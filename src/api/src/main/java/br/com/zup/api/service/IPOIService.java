package br.com.zup.api.service;

import br.com.zup.api.entity.POI;

import java.util.List;

public interface IPOIService {

    /**
     * This method is responsible for return a {@link List}<{@link POI}> of all registered POIs.
     * @return list containing all registered POI.
     */
    List<POI> listAll();


    /**
     * This method is responsible for create a new {@link POI}.
     * @param entity a {@link POI} representing the new {@link POI} that should be saved.
     * @return a {@link POI} based on {@link POI} created.
     */
    POI create(POI entity);


    /**
     * This method is responsible for list all {@link POI}s based on a point of reference (x,y) an also on max distance (d).
     * @param x a non negative integer that represents the coordinate x.
     * @param y a non negative integer that represents the coordinate y.
     * @param d a non negative double that represents the max distance.
     * @return a {@link List}<{@link POI}> containing all the POIs inside the radius of max distance.
     */
    List<POI> listAllByReferencePoint(int x, int y, double d);
}
