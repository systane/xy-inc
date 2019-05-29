package br.com.zup.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class POIDTO {
    private String name;
    private int x;
    private int y;

    public POIDTO(int x, int y){
        if(x > 0 && y > 0){
            this.x = x;
            this.y = y;
        }
        else{
            throw new IllegalArgumentException("the coordinates must be a non negative number");
        }
    }

    public void setX(int x){
        if(x < 0){
            throw new IllegalArgumentException("the coordinate x must be a non negative number");
        }

       this.x = x;
    }

    public void setY(int y){
        if(y < 0){
            throw new IllegalArgumentException("the coordinate y must be a non negative number");
        }

        this.y = y;
    }

    public void setName(String name){
        this.name = name;
    }


}
