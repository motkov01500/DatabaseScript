package cap.databasescript.service;

import cap.databasescript.model.CompanyLocation;
import cap.databasescript.model.CompanyLocationType;

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
}
