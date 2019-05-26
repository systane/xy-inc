package br.com.zup.api.unitTest.endpoint;

import br.com.zup.api.dto.POIDTO;
import br.com.zup.api.endpoint.POIController;
import br.com.zup.api.facade.POIFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest //Habilita o teste apenas para a camada web de enpoint
public class POIControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private POIFacade facade;

    private static final String POI_NAME = "Lanchonete";
    private static final int COORDINATE_X = 27;
    private static final int COORDINATE_Y = 12;

    private static final String PATH = "/poi";

    @Test
    public void listAllUnitTest() throws Exception {
        when(facade.listAll()).thenReturn(Arrays.asList(new POIDTO(POI_NAME, COORDINATE_X, COORDINATE_Y)));

        mockMvc.perform(get(PATH))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(POI_NAME)))
                .andExpect(jsonPath("$[0].x", is(COORDINATE_X)))
                .andExpect(jsonPath("$[0].y", is(COORDINATE_Y)))
                .andDo(print());

        verify(facade, times(1)).listAll();
    }

    @Test
    public void createUnitTest() throws Exception {
        when(facade.listAll()).thenReturn(Arrays.asList(new POIDTO(POI_NAME, COORDINATE_X, COORDINATE_Y)));

//        mockMvc.perform(get(PATH))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].name", is(POI_NAME)))
//                .andExpect(jsonPath("$[0].x", is(COORDINATE_X)))
//                .andExpect(jsonPath("$[0].y", is(COORDINATE_Y)))
//                .andDo(print());
//
//        verify(facade, times(1)).listAll();
    }

    @Test
    public void listAllByReferencePointUnitTest() throws Exception {
        when(facade.listAll()).thenReturn(Arrays.asList(new POIDTO(POI_NAME, COORDINATE_X, COORDINATE_Y)));

//        mockMvc.perform(get(PATH))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].name", is(POI_NAME)))
//                .andExpect(jsonPath("$[0].x", is(COORDINATE_X)))
//                .andExpect(jsonPath("$[0].y", is(COORDINATE_Y)))
//                .andDo(print());
//
//        verify(facade, times(1)).listAll();
    }
}
