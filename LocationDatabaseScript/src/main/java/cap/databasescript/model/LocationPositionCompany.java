package cap.databasescript.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;

@Getter
@Setter
@NoArgsConstructor
public class LocationPositionCompany {

    private Company company;

    private String address;

    @JsonbProperty("lat")
    private double lat;

    @JsonbProperty("lon")
    private double lon;

    @JsonbCreator
    public LocationPositionCompany(@JsonbProperty("lat") double lat, @JsonbProperty("lon")double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "LocationPosition{" +
                "company=" + company +
                ", address='" + address + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                '}' +
                "\n";
    }
}