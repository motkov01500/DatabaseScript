package cap.databasescript.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING, length = 31)
@Getter
@Setter
public abstract class User {

    @Id
    @Column(name = "primary_user_id", nullable = false)
    private Long primaryUserId;

    @Enumerated(jakarta.persistence.EnumType.STRING)
    @Column(name = "LANGUAGE", nullable = false)
    private Language language;

    @Override
    public String toString() {
        return "User{" +
                "primaryUserId=" + primaryUserId +
                ", language=" + language +
                '}';
    }
}
