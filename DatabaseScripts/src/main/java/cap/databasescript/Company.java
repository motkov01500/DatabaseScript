package cap.databasescript;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "company")
@Table(name = "company")
@Getter
@Setter
public class Company {

    @Id
    @Column(name = "primary_company_id", nullable = false)
    private Long primaryCompanyId;

    @Column(name = "name", nullable = false)
    private String name;
}
