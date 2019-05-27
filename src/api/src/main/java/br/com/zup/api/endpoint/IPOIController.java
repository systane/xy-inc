package br.com.zup.api.endpoint;

import br.com.zup.api.dto.POIDTO;
import br.com.zup.api.entity.POI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController (value = "/poi")
@Api(value = "POI", description = "This API is responsible for managing the POI resource")
public interface IPOIController {

    /**
     * This method is responsible for return all registered POIs.
     * @return List containg all registered POIs
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "List all POIs registered")
    List<POIDTO> listAll();


    /**
     * This method is responsible for create a new {@link POI}.
     * @param dto a {@link POIDTO} representing the new {@link POI} which will be saved.
     * @return a {@link POIDTO} based on {@link POI} created.
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Create a new resource (POI) in the database")
    POIDTO create(@RequestBody POIDTO dto);


    /**
     * This method is responsible for list all {@link POI}s based on a point of reference (x,y) an also on max distance (d).
     * @param x a non negative integer that represents the coordinate x.
     * @param y a non negative integer that represents the coordinate y.
     * @param d a non negative double that represents the max distance.
     * @return a {@link List}<{@link POIDTO}> containing all the POIs inside the radius of max distance.
     */
    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "List all POIs based on a point of reference and a max distance")
    List<POIDTO> listAllByReferencePoint(@RequestParam("x" ) int x,
                                         @RequestParam("y") int y,
                                         @RequestParam("d") double d);
}
