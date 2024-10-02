package cap.databasescript.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.SequenceGenerator;

@Entity
@Table(name = "location")
@Getter
@Setter
@NoArgsConstructor
public class CompanyLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_LOCATION_ID")
    @SequenceGenerator(name = "SEQ_LOCATION_ID", sequenceName = "SEQ_LOCATION_ID", allocationSize = 1)
    @Column(name = "location_id", nullable = false)
    private Long locationId;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "address")
    private String address;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String 	city;

    @Column(name = "postal_code")
    private String postalCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "LOCATION_TYPE")
    private CompanyLocationType locationType;

    public CompanyLocation(Double longitude, Double latitude, String address, String country, String city, String postalCode, CompanyLocationType locationType) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.active = true;
        this.country = country;
        this.city = city;
        this.postalCode = postalCode;
        this.locationType = locationType;
    }
}
