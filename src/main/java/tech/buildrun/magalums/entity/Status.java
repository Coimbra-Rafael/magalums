package tech.buildrun.magalums.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_status")
public class Status implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    private String description;

    @AllArgsConstructor
    public enum Values {
        PENDING(1L,"pending"),
        SUCCESS(2L,"success"),
        ERROR(3L,"error"),
        CANCELLED(4L,"cancelled");

        private Long id;
        private String description;

        public Status toStatus() {
            return new Status(id, description);
        }
    }
}
