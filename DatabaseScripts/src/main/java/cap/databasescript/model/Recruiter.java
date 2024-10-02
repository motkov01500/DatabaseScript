package cap.databasescript.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name = "recruiter")
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "primary_user_id")
public class Recruiter extends User{

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", referencedColumnName = "company_id", nullable = false)
    private Company company;
}
