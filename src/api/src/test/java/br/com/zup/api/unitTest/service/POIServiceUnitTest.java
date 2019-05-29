package br.com.zup.api.unitTest.service;

import br.com.zup.api.dto.POIDTO;
import br.com.zup.api.entity.POI;
import br.com.zup.api.repository.IPoiRepository;
import br.com.zup.api.service.POIService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static br.com.zup.api.TestUtils.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class POIServiceUnitTest {

    @InjectMocks
    private POIService service;

    @Mock
    private IPoiRepository repository;

    private static POI poi;
    private static POIDTO poiDto;


    @Test
    public void listAll() throws Exception {
        poi = new POI(POI_NAME, COORDINATE_X, COORDINATE_Y);
        when(repository.findAll()).thenReturn(Arrays.asList(poi));

        List<POI> poiList = service.listAll();
        Assert.assertNotNull(poiList);
        Assert.assertEquals(1, poiList.size());
        Assert.assertEquals(poi.getName(), poiList.get(0).getName());
        Assert.assertEquals(poi.getX(), poiList.get(0).getX());
        Assert.assertEquals(poi.getY(), poiList.get(0).getY());
    }

    @Test
    public void create() throws Exception {
        poi = new POI(POI_NAME, COORDINATE_X, COORDINATE_Y);
        when(repository.save(Mockito.any(POI.class))).thenReturn(poi);

        POI savedPoi = service.create(poi);
        Assert.assertNotNull(savedPoi);
        Assert.assertEquals(poi.getName(), savedPoi.getName());
        Assert.assertEquals(poi.getX(), savedPoi.getX());
        Assert.assertEquals(poi.getY(), savedPoi.getY());
    }

    @Test
    public void listAllByReferencePoint() throws Exception {
        poiDto = new POIDTO(COORDINATE_X, COORDINATE_Y);
        poi = new POI(POI_NAME, COORDINATE_X, COORDINATE_Y);
        poiDto.setName(POI_NAME);
        double maxDistance = 10;

        when(repository.findAll()).thenReturn(Arrays.asList(poi));

        List<POI> dtoList = service.listAllByReferencePoint(poiDto, maxDistance);
        Assert.assertNotNull(dtoList);
        Assert.assertEquals(1, dtoList.size());
        Assert.assertEquals(poi.getName(), dtoList.get(0).getName());
        Assert.assertEquals(poi.getX(), dtoList.get(0).getX());
        Assert.assertEquals(poi.getY(), dtoList.get(0).getY());
    }
}
