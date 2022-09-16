package Model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.lang.annotation.Documented;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class JsonHamsterItem {
    @Id
    private Integer id;
    private String itemJson;
}
