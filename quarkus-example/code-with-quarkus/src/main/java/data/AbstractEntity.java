package data;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "created_date", nullable = false)
    private Instant createdDate;
    @Column(name = "last_modified_date")
    private Instant lastModifiedDate;
}
