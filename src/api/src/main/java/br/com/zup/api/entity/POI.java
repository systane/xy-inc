package br.com.zup.api.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotNull;

@Document(collection = "poi")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class POI {

    @Id
    private String id;

    @NotNull (message = "POI's name must not be null")
    private String name;

    @NotNull (message = "POI's coordenate x must not be null")
    private int x;

    @NotNull (message = "POI's coordenate y must not be null")
    private int y;

    public POI(String name, int x, int y){
        this.name = name;
        this.x = x;
        this.y = y;
    }
}
