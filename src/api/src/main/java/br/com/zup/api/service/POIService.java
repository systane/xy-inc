package br.com.zup.api.service;

import br.com.zup.api.dto.POIDTO;
import br.com.zup.api.entity.POI;
import br.com.zup.api.repository.IPoiRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class POIService implements IPOIService{

    private final IPoiRepository repository;

    @Override
    public List<POI> listAll() {
        return repository.findAll();
    }

    @Override
    public POI create(POI entity) {
        return repository.save(entity);
    }

    @Override
    public List<POI> listAllByReferencePoint(POIDTO referencePoint, double maxDistance) {
        List<POI> pois = listAll();

        return pois.stream()
                .filter(poi -> isNearFromReference(poi, referencePoint, maxDistance))
                .collect(Collectors.toList());
    }

    private boolean isNearFromReference(POI poi, POIDTO referencePoint, double maxDistance) {
        int xDifference = referencePoint.getX() - poi.getX();
        int yDifference = referencePoint.getY() - poi.getY();

        double distanceBetween = Math.sqrt(Math.pow(xDifference, 2) + Math.pow(yDifference, 2));

        return distanceBetween < maxDistance;
    }

}
