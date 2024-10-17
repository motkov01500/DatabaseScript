package cap.databasescript.model;

public class LocationFormData {

    private Long locationId;

    private String label;

    private Double longitude;

    private Double latitude;

    private String address;

    private String country;

    private String city;

    private String postalCode;

    private CompanyLocationType locationType;

    private String additionalInfo;

    private String mapScreenshot;

    public String getMapScreenshot() {
        return mapScreenshot;
    }

    public void setMapScreenshot(String mapScreenshot) {
        this.mapScreenshot = mapScreenshot;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public CompanyLocationType getLocationType() {
        return locationType;
    }

    public void setLocationType(CompanyLocationType locationType) {
        this.locationType = locationType;
    }
}