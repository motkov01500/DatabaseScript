package cap.databasescript;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "company")
@Getter
@Setter
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
}
