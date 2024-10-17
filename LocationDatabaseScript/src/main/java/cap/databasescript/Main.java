package cap.databasescript;

import cap.databasescript.model.*;
import cap.databasescript.service.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private static CompanyService companyService = CompanyService.getINSTANCE();
    private static LocationService locationService = LocationService.getINSTANCE();
    private static JobSeekerService jobSeekerService = JobSeekerService.getINSTANCE();

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter main or secondary or jobseeker(for company main locations and company secondary locations):");
        String userInput = scanner.next();
        if(userInput.toLowerCase().trim().equals("main")) {
            companyMainLocationsLogic();
        }
        if(userInput.toLowerCase().trim().equals("secondary")) {
            companySecondaryLocationsLogic();
        }
        if(userInput.toLowerCase().trim().equals("jobseeker")) {
            jobSeekerLogic();
        }
    }

    public static void companyMainLocationsLogic() {
        Scanner scanner = new Scanner(System.in);
        String userResponse = "";
        ApiConsume api = ApiConsume.getINSTANCE();
        Map<Company, String> findedCompanies = companyService.getAllCompaniesRelatedToAddress();
        List<LocationPositionCompany> coordinates = api.consumeTomTomForCompanyMainLocation(findedCompanies);
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

    public static void companySecondaryLocationsLogic() throws IOException {
        ApiConsume api = ApiConsume.getINSTANCE();
        List<CompanyLocation> test = new ArrayList<>(locationService.getAllSecondaryLocations().keySet());
        List<LocationPositionSecondary> coordinates = api.consumeTomTomForCompanySecondaryLocations(test);
        System.out.println(coordinates);
        System.out.println(locationService.updating(coordinates));
        printSecondaryLocations();
    }

    public static void createNewLocation(List<LocationPositionCompany> locationList) {
        for (LocationPositionCompany locationPosition : locationList) {
            companyService.createdMainLocationToCurrentCompany(locationPosition);
        }
    }

    public static void printSecondaryLocations() {
        Map<CompanyLocation, String> secondaryLocations = locationService.getAllSecondaryLocations();
        for (CompanyLocation location : secondaryLocations.keySet()) {
            System.out.println(location + "\n");
            System.out.println(secondaryLocations.get(location));
            System.out.println("--------------------------------------------------\n");
        }
    }

    public static void jobSeekerLogic() {
        ApiConsume api = ApiConsume.getINSTANCE();
        List<JobSeeker> list = jobSeekerService.getJobSeekers();
        List<LocationPositionJobSeeker> result = api.consumeTomTomForJobSeeker(list).stream().filter(res->
                        res.getLat() != 0.0 && res.getLon() != 0.0)
                .collect(Collectors.toList());
        for (LocationPositionJobSeeker location: result) {
            JobSeeker jobSeeker = jobSeekerService.getJobSeekerById(location.getJobSeeker().getPrimaryUserId());
            jobSeeker.setAddressLatitude(location.getLat());
            jobSeeker.setAddressLongitude(location.getLon());
            jobSeeker.setAddress(location.getAddress());
            jobSeekerService.update(jobSeeker);
        }
    }
}