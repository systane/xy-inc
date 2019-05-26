package br.com.zup.api.service;

import br.com.zup.api.dto.POIDTO;
import br.com.zup.api.entity.POI;
import br.com.zup.api.repository.POIRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class POIService implements IPOIService{

    private final POIRepository poiRepository;

    @Override
    public List<POI> listAll() {
        return listAllPOIs();
    }

    @Override
    public POI create(POI entity) {
        return entity;
    }

    @Override
    public List<POI> listAllByReferencePoint(int x, int y, double d) {
        return listAllByReferencePoint(x, y, d, listAllPOIs());
    }

    private List<POI> listAllPOIs(){
        List<POI> pois = new ArrayList<>();
        int[] coordinatesX = coordinatesX();
        int[] coordinatesY = coordinatesY();
        String[] names = names();

        for(int x: coordinatesX){
            pois.add(new POI(names[x], x, coordinatesY[x]));
        }

        return pois;
    }

    private List<POI> listAllByReferencePoint(int x, int y, double d, List<POI> pois){
        POIDTO referencePoint = new POIDTO();
        referencePoint.setX(20);
        referencePoint.setY(10);

        return pois.stream().filter(poi -> filterPOISByDistance(poi, referencePoint, d)).collect(Collectors.toList());
    }

    private boolean filterPOISByDistance(POI poi, POIDTO referencePoint, double d) {
        int xDifference = referencePoint.getX() - poi.getX();
        int yDifference = referencePoint.getY() - poi.getY();

        double distanceBetween = Math.sqrt(xDifference) + Math.sqrt(yDifference);

        return distanceBetween < d;
    }

    private int[] coordinatesX(){
        int[] coordinatesX = {27, 31, 15, 19, 12, 23, 28};
        return coordinatesX;
    }

    private int[] coordinatesY(){
        int[] coordinatesY = {12, 18, 12, 21, 8, 6, 2};
        return coordinatesY;
    }

    private String[] names(){
        String[] names = {"Lanchonete", "Posto", "Joalheria", "Floricultura", "Pub", "Supermercado", "Churrascaria"};
        return names;
    }
}
