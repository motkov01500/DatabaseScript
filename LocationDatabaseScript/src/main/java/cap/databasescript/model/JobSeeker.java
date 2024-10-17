package cap.databasescript.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name = "jobseeker")
@PrimaryKeyJoinColumn(name = "PRIMARY_USER_ID")
@Getter
@Setter
@NoArgsConstructor
public class JobSeeker extends User{

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "CITY")
    private String city;

    @Column(name="ADDRESS")
    private String address;

    @Column(name="STREET")
    private String street;

    @Column(name="HOUSE_NUMBER")
    private String houseNumber;

    @Column(name="POSTAL_CODE")
    private String postalCode;

    @Column(name = "ADDRESS_LATITUDE")
    private Double addressLatitude;

    @Column(name = "ADDRESS_LONGITUDE")
    private Double addressLongitude;

    @Override
    public String toString() {
        return "JobSeekerBE{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", addressLatitude=" + addressLatitude +
                ", addressLongitude=" + addressLongitude +
                "}\n";
    }

    public JobSeeker(JobSeeker jobSeeker) {
        this.address = jobSeeker.getAddress();
        this.addressLatitude = jobSeeker.getAddressLatitude();
        this.addressLongitude = jobSeeker.getAddressLongitude();
    }
}
