package cap.databasescript.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;

@Getter
@Setter
@NoArgsConstructor
public class LocationPositionJobSeeker {

    private JobSeeker jobSeeker;

    private String address;

    @JsonbProperty("lat")
    private double lat;

    @JsonbProperty("lon")
    private double lon;

    @JsonbCreator
    public LocationPositionJobSeeker(@JsonbProperty double lat, @JsonbProperty double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "LocationPositionSecondary{" +
                "companyLocation=" + jobSeeker +
                ", address='" + address + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}
