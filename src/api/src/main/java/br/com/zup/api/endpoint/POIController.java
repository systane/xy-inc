package br.com.zup.api.endpoint;

import br.com.zup.api.dto.POIDTO;
import br.com.zup.api.entity.POI;
import br.com.zup.api.facade.IPOIFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "/")
@Api(value = "POI", description = "This API is responsible for managing the POI resource")
@AllArgsConstructor
public class POIController{

    private final IPOIFacade facade;

    
    /**
     * This method is responsible for return all registered POIs.
     * @return List containg all registered POIs
     */
    @GetMapping(value = "poi",  produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List all POIs registered")
    public ResponseEntity<List<POIDTO>> listAll() {
        return new ResponseEntity<>(facade.listAll(), HttpStatus.OK);
    }


    /**
     * This method is responsible for create a new {@link POI}.
     * @param dto a {@link POIDTO} representing the new {@link POI} which will be saved.
     * @return a {@link POIDTO} based on {@link POI} created.
     */
    @PostMapping(value = "poi",  produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create a new resource (POI) in the database")
    public ResponseEntity<POIDTO> create(@RequestBody POIDTO dto) {
        return new ResponseEntity<>(facade.create(dto), HttpStatus.CREATED);
    }


    /**
     * This method is responsible for list all {@link POI}s based on a point of reference (x,y) an also on max distance (d).
     * @param x a non negative integer that represents the coordinate x.
     * @param y a non negative integer that represents the coordinate y.
     * @param d a non negative double that represents the max distance.
     * @return a {@link List}<{@link POIDTO}> containing all the POIs inside the radius of max distance.
     */
    @GetMapping(value = "poi/filter", params = {"x", "y", "d"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List all POIs based on a point of reference and a max distance")
    public ResponseEntity<List<POIDTO>> listAllByReferencePoint(
            @RequestParam("x") int x,
            @RequestParam("y") int y,
            @RequestParam("d") int d) {
        return new ResponseEntity<>(facade.listAllByReferencePoint(x, y, d), HttpStatus.OK);
    }
}
