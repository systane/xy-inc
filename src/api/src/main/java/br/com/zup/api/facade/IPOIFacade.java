package br.com.zup.api.facade;

import br.com.zup.api.dto.POIDTO;
import br.com.zup.api.entity.POI;

import java.util.List;

public interface IPOIFacade {

    /**
     * This method is responsible for {@link List}<{@link POIDTO}> all registered POIs.
     * @return list with all registered POIs
     */
    List<POIDTO> listAll();


    /**
     * This method is responsible for create a new {@link POI}.
     * @param dto a {@link POIDTO} representing the new {@link POI} which just have been saved.
     * @return a {@link POIDTO} based on {@link POI} created.
     */
    POIDTO create(POIDTO dto);


    /**
     * This method is responsible for list all {@link POI}s based on a point of reference (x,y) an also on max distance (d).
     * @param x a non negative integer that represents the coordinate x.
     * @param y a non negative integer that represents the coordinate y.
     * @param maxDistance a non negative double that represents the max distance.
     * @return a {@link List}<{@link POIDTO}> containing all the POIs inside the radius of max distance.
     */
    List<POIDTO> listAllByReferencePoint(int x, int y, double maxDistance);
}
