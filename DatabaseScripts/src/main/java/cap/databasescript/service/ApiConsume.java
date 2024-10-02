package cap.databasescript.service;

import cap.databasescript.model.Company;
import cap.databasescript.model.LocationPosition;
import cap.databasescript.util.JsonUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApiConsume {

    private static ApiConsume INSTANCE;
    private CompanyService companyService = CompanyService.getINSTANCE();
    private final String API_KEY = "AtqjjGJpGuNxwWOxBMYlDM5JYZ7tqn3r";
    private RecruiterService recruiterService = RecruiterService.getINSTANCE();
    private ApiConsume() {
    }

    public static ApiConsume getINSTANCE() {
        if(INSTANCE == null) {
            INSTANCE = new ApiConsume();
        }
        return INSTANCE;
    }

    public List<LocationPosition> consumeTomTom(Map<Company, String> companies) {
        List<LocationPosition> locationPositions = new ArrayList<>();
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            for (Company key  : companies.keySet()) {
                String userInput = companies.get(key).replaceAll("\\s+", ",");
                System.out.println(userInput);
                HttpGet request = new HttpGet("https://api.tomtom.com/search/2/geocode/" + userInput + ".json?key=" + API_KEY + "&idxSet=PAD&maxFuzzyLevel=1");

                CloseableHttpResponse response = httpClient.execute(request);
                HttpEntity entity = response.getEntity();

                if (entity != null) {
                    String jsonResponse = EntityUtils.toString(entity);
                    locationPositions.add(JsonUtils.parseLocations(JsonUtils.createJSONFromString(jsonResponse).toString(), key));
                }
                Thread.sleep(2000);
                System.out.println("completed iteration!");
            }
        } catch (IOException e) {
            return new ArrayList<>();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return locationPositions;
    }
}
