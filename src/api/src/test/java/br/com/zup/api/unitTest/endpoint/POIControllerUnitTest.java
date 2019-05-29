package br.com.zup.api.unitTest.endpoint;

import br.com.zup.api.dto.POIDTO;
import br.com.zup.api.endpoint.POIController;
import br.com.zup.api.facade.IPOIFacade;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static br.com.zup.api.TestUtils.COORDINATE_X;
import static br.com.zup.api.TestUtils.COORDINATE_Y;
import static br.com.zup.api.TestUtils.POI_NAME;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(MockitoJUnitRunner.class)
public class POIControllerUnitTest {
    private static final String PATH = "/poi";

    private MockMvc mockMvc;

    @Mock
    private IPOIFacade facade;

    @InjectMocks
    private POIController poiController;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(poiController).build();
    }


    @Test
    public void listAllTest() throws Exception {
        when(facade.listAll()).thenReturn(Arrays.asList(new POIDTO(POI_NAME, COORDINATE_X, COORDINATE_Y)));

        mockMvc.perform(get(PATH).contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].name", is(POI_NAME)))
            .andExpect(jsonPath("$[0].x", is(COORDINATE_X)))
            .andExpect(jsonPath("$[0].y", is(COORDINATE_Y))).andDo(print());

        verify(facade, times(1)).listAll();
    }

    @Test
    public void createTest() throws Exception {
        POIDTO poidto = new POIDTO(POI_NAME, COORDINATE_X, COORDINATE_Y);
        when(facade.create(Mockito.any(POIDTO.class))).thenReturn(poidto);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPoi = objectMapper.writeValueAsString(poidto);

        mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonPoi))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is(POI_NAME)))
                .andExpect(jsonPath("$.x", is(COORDINATE_X)))
                .andExpect(jsonPath("$.y", is(COORDINATE_Y)))
                .andDo(print());
    }

    @Test
    public void listAllByReferencePointTest() throws Exception {
        when(facade.listAllByReferencePoint(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyDouble()))
                .thenReturn(Arrays.asList(new POIDTO(POI_NAME, COORDINATE_X, COORDINATE_Y)));

        mockMvc.perform(get(PATH + "/filter?x=10&y=12&d=10").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(POI_NAME)))
                .andExpect(jsonPath("$[0].x", is(COORDINATE_X)))
                .andExpect(jsonPath("$[0].y", is(COORDINATE_Y))).andDo(print());
    }
}
