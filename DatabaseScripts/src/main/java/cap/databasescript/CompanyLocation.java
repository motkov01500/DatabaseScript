package cap.databasescript;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "location")
@Getter
@Setter
public class CompanyLocation {

    @Id
    @Column(name = "location_id", nullable = false)
    private Long locationId;
}
