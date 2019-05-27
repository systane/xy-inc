package br.com.zup.api.unitTest.facade;

import static br.com.zup.api.TestUtils.COORDINATE_X;
import static br.com.zup.api.TestUtils.COORDINATE_Y;
import static br.com.zup.api.TestUtils.POI_NAME;

import br.com.zup.api.dto.POIDTO;
import br.com.zup.api.entity.POI;
import br.com.zup.api.facade.POIFacade;
import br.com.zup.api.service.POIService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class POIFacadeUnitTest {

    @InjectMocks
    private POIFacade facade;

    @InjectMocks
    private ModelMapper modelMapper;

    @MockBean
    private POIService service;

    private static POI poi;
    private static POIDTO poiDto;

    @Before
    public void setUp(){
        modelMapper = new ModelMapper();
    }

    @Test
    public void listAll() throws Exception {
        poi = new POI(POI_NAME, COORDINATE_X, COORDINATE_Y);
        when(service.listAll()).thenReturn(Arrays.asList(poi));

        List<POIDTO> dtoList = facade.listAll();
        Assert.assertNotNull(dtoList);
        Assert.assertEquals(1, dtoList.size());
        Assert.assertEquals(poi.getName(), dtoList.get(0).getName());
        Assert.assertEquals(poi.getX(), dtoList.get(0).getX());
        Assert.assertEquals(poi.getY(), dtoList.get(0).getY());
    }

    @Test
    public void create() throws Exception {
        poiDto = new POIDTO(POI_NAME, COORDINATE_X, COORDINATE_Y);
        when(service.create(Mockito.any(POI.class))).thenReturn(poi);

        POIDTO savedPoi = facade.create(poiDto);
        Assert.assertNotNull(savedPoi);
        Assert.assertEquals(poiDto.getName(), savedPoi.getName());
        Assert.assertEquals(poiDto.getX(), savedPoi.getX());
        Assert.assertEquals(poiDto.getY(), savedPoi.getY());
    }

    @Test
    public void listAllByReferencePoint() throws Exception {
        poi = new POI(POI_NAME, COORDINATE_X, COORDINATE_Y);
        double maxDistance = 10;

        when(service.listAllByReferencePoint(Mockito.any(POIDTO.class), Mockito.anyDouble()))
                .thenReturn(Arrays.asList(poi));

        List<POIDTO> dtoList = facade.listAllByReferencePoint(COORDINATE_X, COORDINATE_Y, maxDistance);
        Assert.assertNotNull(dtoList);
        Assert.assertEquals(1, dtoList.size());
        Assert.assertEquals(poi.getName(), dtoList.get(0).getName());
        Assert.assertEquals(poi.getX(), dtoList.get(0).getX());
        Assert.assertEquals(poi.getY(), dtoList.get(0).getY());
    }
}
