package cap.databasescript.util;

import cap.databasescript.model.Company;
import cap.databasescript.model.LocationPosition;
import cap.databasescript.service.CompanyService;

import java.io.StringReader;
import javax.json.*;

public class JsonUtils {

    private static CompanyService companyService = CompanyService.getINSTANCE();

    public static JsonObject createJSONFromString(String jsonStr) {
        if (jsonStr.isEmpty()) {
            return null;
        }
        try (JsonReader jsonReader = Json.createReader(new StringReader(jsonStr))) {
            return jsonReader.readObject();
        } catch (JsonException ex) {
            System.out.println(ex);
            System.out.println(jsonStr);
        }
        return null;
    }


    public static LocationPosition parseLocations(String jsonString, Company company) {
        JsonObject parsedResponse = createJSONFromString(jsonString);
        LocationPosition position = new LocationPosition();

        JsonArray results = parsedResponse.getJsonArray("results");
        position.setCompany(company);
        position.setAddress(companyService.concatenateAddressForResponse(company));
        if (results.isEmpty()) {
            return position;
        }
        JsonObject resultObject = results.getValuesAs(JsonObject.class).get(0);
        JsonObject positionObject = resultObject.getJsonObject("position");

        if (positionObject.containsKey("lon")) {
            position.setLon(positionObject.getJsonNumber("lon").doubleValue());
        }

        if (positionObject.containsKey("lat")) {
            position.setLat(positionObject.getJsonNumber("lat").doubleValue());
        }

        return position;
    }
}
