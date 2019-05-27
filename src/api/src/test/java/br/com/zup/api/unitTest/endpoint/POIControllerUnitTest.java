package br.com.zup.api.unitTest.endpoint;

import br.com.zup.api.dto.POIDTO;
import br.com.zup.api.facade.POIFacade;
import br.com.zup.api.unitTest.BaseUnitTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.junit.Assert.*;

import java.util.Arrays;

import static br.com.zup.api.TestUtils.COORDINATE_X;
import static br.com.zup.api.TestUtils.COORDINATE_Y;
import static br.com.zup.api.TestUtils.POI_NAME;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class POIControllerUnitTest extends BaseUnitTest {
    private static final String PATH = "/poi";

    @MockBean
    private POIFacade facade;

    @Before
    public void setUp() {
        super.setUp();
    }


    @Test
    public void listAllTest() throws Exception {
        when(facade.listAll()).thenReturn(Arrays.asList(new POIDTO(POI_NAME, COORDINATE_X, COORDINATE_Y)));

        mockMvc.perform(get(PATH).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

//        String content = mvcResult.getResponse().getContentAsString();
//        POIDTO[] poidtos = super.mapFromJson(content, POIDTO[].class);
//
//        assertNotNull(poidtos);
//        assertTrue(poidtos.length > 0);
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].name", is(POI_NAME)))
//                .andExpect(jsonPath("$[0].x", is(COORDINATE_X)))
//                .andExpect(jsonPath("$[0].y", is(COORDINATE_Y)))
//                .andDo(print());

        verify(facade, times(1)).listAll();
    }

    @Test
    public void createTest() throws Exception {
        POIDTO poidto = new POIDTO(POI_NAME, COORDINATE_X, COORDINATE_Y);
        when(facade.listAll()).thenReturn(Arrays.asList(poidto));

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPoi = objectMapper.writeValueAsString(poidto);

        mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonPoi))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is(POI_NAME)))
                .andExpect(jsonPath("$.x", is(COORDINATE_X)))
                .andExpect(jsonPath("$.y", is(COORDINATE_Y)))
                .andDo(print());

        verify(facade, times(1)).listAll();
    }

    @Test
    public void listAllByReferencePointTest() throws Exception {
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
