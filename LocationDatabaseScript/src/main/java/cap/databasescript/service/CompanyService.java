package cap.databasescript.service;

import cap.databasescript.model.Company;
import cap.databasescript.model.CompanyLocation;
import cap.databasescript.model.CompanyLocationType;
import cap.databasescript.model.LocationPositionCompany;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class CompanyService extends ScriptService<Company> {

    private static CompanyService INSTANCE;
    private LocationService locationService = LocationService.getINSTANCE();

    private CompanyService() {
        super(Company.class);
    }

    public static CompanyService getINSTANCE() {
        if(INSTANCE == null) {
            INSTANCE = new CompanyService();
        }
        return INSTANCE;
    }

    public Map<Company, String> getAllCompaniesRelatedToAddress() {
        Map<Company, String> companies = new HashMap<>();
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
        CriteriaQuery<Company> query = getCriteriaQuery();
        Root<Company> companyRoot = query.from(entity);
        query.select(companyRoot);
        List<Company> result = getEntityByCriteria(query).getResultList();
                result.stream().filter(company-> company.getCity() != null && company.getStreet() != null && company.getHouseNumber() != null && company.getPostalCode() != null)
                .forEach(company -> companies.put(company, concatenateAddressForAPI(company)));
        return companies;
    }

    public String concatenateAddressForAPI(Company company) {
        Locale locale = new Locale("", company.getCountry());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(company.getCity()).append(",").append(locale.getDisplayName()).append(",")
                .append(company.getStreet()).append(",").append(company.getHouseNumber()).append(",")
                .append(company.getPostalCode());
        return stringBuilder.toString();
    }

    public String concatenateAddressForResponse(Company company) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(company.getStreet()).append(" ").append(company.getHouseNumber()).append(",").append(company.getCity()).append(" ").append(" ")
                .append(company.getPostalCode());
        return stringBuilder.toString();
    }

    public String createdMainLocationToCurrentCompany(LocationPositionCompany locationPosition) {
        CompanyLocation companyLocation = new CompanyLocation(
                locationPosition.getLon() == 0.0 ? null : locationPosition.getLon(),
                locationPosition.getLat() == 0.0 ? null : locationPosition.getLat(),
                locationPosition.getAddress(),
                locationPosition.getCompany().getCountry(),
                locationPosition.getCompany().getCity(),
                locationPosition.getCompany().getPostalCode(),
                CompanyLocationType.MAIN
        );
        CompanyLocation mainLocation = locationService.createLocation(companyLocation);
        Company currentCompany = locationPosition.getCompany();
        currentCompany.addLocation(mainLocation);
        update(currentCompany);
        return "new location is created";
    }


    public String concatenateAddressForResponses(CompanyLocation company) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(company.getStreet()).append(" ").append(company.getHouseNumber()).append(",").append(company.getCity()).append(" ").append(" ")
                .append(company.getPostalCode());
        return stringBuilder.toString();
    }
}
