package cap.databasescript.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "company")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @Column(name = "primary_company_id", nullable = false)
    private Long primaryCompanyId;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "house_number")
    private String houseNumber;

    @Column(name = "postal_code")
    private String postalCode;

    @OneToMany(fetch=FetchType.LAZY, cascade={CascadeType.REFRESH})
    @JoinTable(name="company_rel_location", joinColumns={ @JoinColumn(name="company_id", referencedColumnName="company_id") } , inverseJoinColumns={ @JoinColumn(name="location_id") } )
    private List<CompanyLocation> companyLocations;

    public void addLocation(CompanyLocation location) {
        if (this.companyLocations == null) {
            companyLocations = new ArrayList<>();
        }
        companyLocations.add(location);
    }


    @Override
    public String toString() {
        return "Company{" +
                "primaryCompanyId=" + primaryCompanyId +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}
