package cap.databasescript.util;

import cap.databasescript.model.*;
import cap.databasescript.service.CompanyService;
import cap.databasescript.service.JobSeekerService;
import cap.databasescript.service.LocationService;

import java.io.StringReader;
import javax.json.*;

public class JsonUtils {

    private static CompanyService companyService = CompanyService.getINSTANCE();
    private static LocationService locationService = LocationService.getINSTANCE();
    private static JobSeekerService jobSeekerService = JobSeekerService.getINSTANCE();

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


    public static LocationPositionCompany parseLocations(String jsonString, Company company) {
        JsonObject parsedResponse = createJSONFromString(jsonString);
        LocationPositionCompany position = new LocationPositionCompany();

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

    public static LocationPositionSecondary parseLocationsSecondary(String jsonString, CompanyLocation location) {
        JsonObject parsedResponse = createJSONFromString(jsonString);
        LocationPositionSecondary position = new LocationPositionSecondary();
        position.setCompanyLocation(location);

        JsonArray results = parsedResponse.getJsonArray("results");
        position.setAddress(companyService.concatenateAddressForResponses(location));
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

    public static LocationPositionJobSeeker parseLocationsJobSeeker(String jsonString, JobSeeker jobSeeker) {
        JsonObject parsedResponse = createJSONFromString(jsonString);
        LocationPositionJobSeeker position = new LocationPositionJobSeeker();
        position.setJobSeeker(jobSeeker);
        if(parsedResponse.getJsonArray("results") != null) {
            JsonArray results = parsedResponse.getJsonArray("results");
            position.setAddress(jobSeekerService.concatenateAddressForResponses(jobSeeker));
            if (results.isEmpty()) {
                return position;
            }
            JsonObject resultObject = results.getValuesAs(JsonObject.class).get(0);
            JsonObject positionObject = resultObject.getJsonObject("position");

            if (positionObject.containsKey("lon")) {
                position.setLon(positionObject.getJsonNumber("lon").doubleValue());
                jobSeeker.setAddressLongitude(positionObject.getJsonNumber("lon").doubleValue());
            }

            if (positionObject.containsKey("lat")) {
                position.setLat(positionObject.getJsonNumber("lat").doubleValue());
                jobSeeker.setAddressLatitude(positionObject.getJsonNumber("lat").doubleValue());
            }
        }
        return position;
    }
}
