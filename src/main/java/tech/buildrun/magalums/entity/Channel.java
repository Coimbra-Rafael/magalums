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
@Table(name = "tb_channel")
public class Channel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long channelId;

    private String description;

    @AllArgsConstructor
    public enum Values {
        EMAIL(1L,"email"),
        SMS(2L,"sms"),
        PUSH(3L,"push"),
        WHATSAPP(4L,"whatsapp");

        private Long id;
        private String description;

        public Channel toChannel() {
            return new Channel(id, description);
        }
    }
}
