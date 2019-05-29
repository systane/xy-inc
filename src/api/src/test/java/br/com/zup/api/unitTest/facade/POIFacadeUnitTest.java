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
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class POIFacadeUnitTest {

    @InjectMocks
    private POIFacade facade;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private POIService service;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private static POI poi;
    private static POIDTO poiDto;

    private static final String NEGATIVE_DISTANCE_ERROR_MESSAGE = "the maxDistance must be a non negative number";

    @Before
    public void setUp(){
        poiDto = new POIDTO(POI_NAME, COORDINATE_X, COORDINATE_Y);
        poi = new POI(POI_NAME, COORDINATE_X, COORDINATE_Y);

        when(modelMapper.map(Mockito.any(POI.class), Mockito.any())).thenReturn(poiDto);
        when(modelMapper.map(Mockito.any(POIDTO.class), Mockito.any())).thenReturn(poi);
    }

    @Test
    public void listAll() throws Exception {
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
        when(service.create(Mockito.any(POI.class))).thenReturn(poi);

        POIDTO savedPoi = facade.create(poiDto);
        Assert.assertNotNull(savedPoi);
        Assert.assertEquals(poiDto.getName(), savedPoi.getName());
        Assert.assertEquals(poiDto.getX(), savedPoi.getX());
        Assert.assertEquals(poiDto.getY(), savedPoi.getY());
    }

    @Test
    public void listAllByReferencePoint() throws Exception {
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

    @Test
    public void listAllByReferencePointWithNegativeDistance() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(NEGATIVE_DISTANCE_ERROR_MESSAGE);

        double maxDistance = -10;

        when(service.listAllByReferencePoint(Mockito.any(POIDTO.class), Mockito.anyDouble()))
                .thenReturn(Arrays.asList(poi));

        facade.listAllByReferencePoint(COORDINATE_X, COORDINATE_Y, maxDistance);
    }
}
