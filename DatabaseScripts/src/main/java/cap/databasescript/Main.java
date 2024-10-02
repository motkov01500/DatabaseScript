package cap.databasescript;

import cap.databasescript.model.Company;
import cap.databasescript.model.CompanyLocation;
import cap.databasescript.model.CompanyLocationType;
import cap.databasescript.model.LocationPosition;
import cap.databasescript.service.*;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static CompanyService companyService = CompanyService.getINSTANCE();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userResponse = "";
        ApiConsume api = ApiConsume.getINSTANCE();
        Map<Company, String> findedCompanies = companyService.getAllCompaniesRelatedToAddress();
        List<LocationPosition> coordinates = api.consumeTomTom(findedCompanies);
        System.out.println(coordinates);
        System.out.println("Do you want to proceed, choose between yes or no:");
        userResponse = scanner.next();
        System.out.println("-------------------------------------------------------------------------");
        if(userResponse.intern().toLowerCase().trim().equals("yes")) {
            createNewLocation(coordinates);
        } else {
            System.out.println("You choose not to proceed.");
        }
    }

    public static void createNewLocation(List<LocationPosition> locationList) {
        for (LocationPosition locationPosition: locationList) {
            companyService.createdMainLocationToCurrentCompany(locationPosition);
        }
    }
}