package br.com.zup.api.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "poi")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class POI {

    @Id
    private String id;
    private String name;
    private int x;
    private int y;

    public POI(String name, int x, int y){
        this.name = name;
        this.x = x;
        this.y = y;
    }
}
