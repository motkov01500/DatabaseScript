package cap.databasescript.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;

@Getter
@Setter
@NoArgsConstructor
public class LocationPositionSecondary {

    private CompanyLocation companyLocation;

    private String address;

    @JsonbProperty("lat")
    private double lat;

    @JsonbProperty("lon")
    private double lon;

    @JsonbCreator
    public LocationPositionSecondary(@JsonbProperty double lat, @JsonbProperty double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "LocationPositionSecondary{" +
                "companyLocation=" + companyLocation +
                ", address='" + address + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}
