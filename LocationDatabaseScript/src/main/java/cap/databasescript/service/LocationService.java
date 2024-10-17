package cap.databasescript.service;

import cap.databasescript.model.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class LocationService extends ScriptService<CompanyLocation> {

    private static LocationService INSTANCE;

    public LocationService() {
        super(CompanyLocation.class);
    }

    public static LocationService getINSTANCE() {
        if(INSTANCE == null) {
            INSTANCE = new LocationService();
        }
        return INSTANCE;
    }

    public CompanyLocation createLocation(CompanyLocation companyLocation) {
        create(companyLocation);
        return companyLocation;
    }

    public Map<CompanyLocation, String> getAllSecondaryLocations() {
        Map<CompanyLocation, String> result = new HashMap<>();
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
        CriteriaQuery<CompanyLocation> query = getCriteriaQuery();
        Root<CompanyLocation> companyLocationRoot = query.from(entity);
        Predicate secondaryLocationType = criteriaBuilder.equal(companyLocationRoot.get("locationType"), CompanyLocationType.SECONDARY);
        Predicate activeLocations = criteriaBuilder.equal(companyLocationRoot.get("active"), true);
        query.select(companyLocationRoot)
                .where(criteriaBuilder.and(secondaryLocationType,activeLocations));
        List<CompanyLocation> results = getEntityByCriteria(query).getResultList();
        results.stream()
            .filter(location -> location.getCity() != null && location.getStreet() != null && location.getHouseNumber() != null && location.getPostalCode() != null )
            .forEach(location -> result.put(location, concatenateAddressForAPI(location)));
        return result;
    }

    public String concatenateAddressForAPI(LocationPositionSecondary location) {
        Locale locale = new Locale("", location.getCompanyLocation().getCountry());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(location.getCompanyLocation().getCity()).append(",").append(locale.getDisplayName()).append(",")
                .append(location.getCompanyLocation().getStreet()).append(",").append(location.getCompanyLocation().getHouseNumber()).append(",")
                .append(location.getCompanyLocation().getPostalCode());
        return stringBuilder.toString();
    }

    public String concatenateAddressForAPI(CompanyLocation company) {
        Locale locale = new Locale("", company.getCountry());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(company.getCity()).append(",").append(locale.getDisplayName()).append(",")
                .append(company.getStreet()).append(",").append(company.getHouseNumber()).append(",")
                .append(company.getPostalCode());
        return stringBuilder.toString();
    }

    public String concatenateAddressForAPIJobSeeker(JobSeeker jobSeeker) {
        Locale locale = new Locale("");
        if(jobSeeker.getCountry()!= null) {
            locale = new Locale("", jobSeeker.getCountry());
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(jobSeeker.getCity()).append(",").append(locale.getDisplayName()).append(",")
                .append(jobSeeker.getStreet()).append(",").append(jobSeeker.getHouseNumber()).append(",")
                .append(jobSeeker.getPostalCode());
        return stringBuilder.toString();
    }

    public CompanyLocation loadLocation(LocationPositionSecondary data, CompanyLocation entity) {
        if(data.getCompanyLocation().getLocationId() != null) {
            entity.setLocationId(data.getCompanyLocation().getLocationId());
        }
        if (data.getCompanyLocation().getCountry() != null) {
            entity.setCountry(data.getCompanyLocation().getCountry());
        }
        if (data.getCompanyLocation().getCity() != null) {
            entity.setCity(data.getCompanyLocation().getCity());
        }
        if (data.getCompanyLocation().getPostalCode() != null) {
            entity.setPostalCode(data.getCompanyLocation().getPostalCode());
        }
        entity.setLocationType(CompanyLocationType.SECONDARY);
        entity.setAddress(data.getAddress());
        entity.setLatitude(data.getCompanyLocation().getLatitude());
        entity.setLongitude(data.getCompanyLocation().getLongitude());
        return entity;
    }

    public String updating(List<LocationPositionSecondary> locations) throws IOException {
        for (LocationPositionSecondary location: locations) {
            CompanyLocation createdLocation = new CompanyLocation();
            CompanyLocation newLocation = loadLocation(location, createdLocation);
            String filePath = "defaultMapImage.txt";
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            String content = String.join("\n", lines);
            newLocation.setMapScreenshot(content);
            update(newLocation);
        }
        return "updated";
    }
}
