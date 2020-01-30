package com.stackroute.newz.test.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.stackroute.newz.controller.NewsSourceController;
import com.stackroute.newz.model.NewsSource;
import com.stackroute.newz.service.NewsSourceService;
import com.stackroute.newz.util.exception.NewsSourceNotFoundException;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class NewsSourceControllerTest {

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private MockMvc mockMvc;
    private NewsSource newsSource;
    @MockBean
    private NewsSourceService newssourceService;
    @InjectMocks
    private NewsSourceController newsSourceController;
    private List<NewsSource> allNewssource = null;


    @BeforeEach
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(newsSourceController).build();
        newsSource = new NewsSource();

        newsSource.setNewsSourceId(1);
        newsSource.setNewsSourceName("CNN");
        newsSource.setNewsSourceDesc("CNN - US");
        newsSource.setNewsSourceCreatedBy("Becky123");
        newsSource.setNewsSourceCreationDate();

        allNewssource = new ArrayList<>();
        allNewssource.add(newsSource);


    }

    @Test
    public void createNewsSourceSuccess() throws Exception {
        when(newssourceService.addNewsSource(any())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/newssource")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(newsSource)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void createNewsSourceFailure() throws Exception {

        when(newssourceService.addNewsSource(any())).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/newssource")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(newsSource)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());

    }


    @Test
    public void deleteNewssourceSuccess() throws Exception {

        when(newssourceService.deleteNewsSource(1)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/newssource/1")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void deleteNewssourceFailure() throws Exception {

        when(newssourceService.deleteNewsSource(1)).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/newssource/1")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void updateNewssourceSuccess() throws Exception {

        when(newssourceService.updateNewsSource(any(), eq(newsSource.getNewsSourceId()))).thenReturn(newsSource);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/newssource/1")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(newsSource)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateNewssourceFailure() throws Exception {

        when(newssourceService.updateNewsSource(any(), eq(newsSource.getNewsSourceId()))).thenThrow(NewsSourceNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/newssource/1")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(newsSource)))
                .andExpect(MockMvcResultMatchers.status().isNotFound()).andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void getNewssourceByIdSuccess() throws Exception {
        when(newssourceService.getNewsSourceById(newsSource.getNewsSourceCreatedBy(),newsSource.getNewsSourceId())).thenReturn(newsSource);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/newssource/Becky123/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void getNewssourceByIdFailure() throws Exception {
    	int newssourceId = newsSource.getNewsSourceId();
        when(newssourceService.getNewsSourceById(newsSource.getNewsSourceCreatedBy(),newssourceId)).thenThrow(NewsSourceNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/newssource/Becky123/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }


    private static String asJsonString(final Object obj) {
        try {
        	ObjectMapper objmapper = new ObjectMapper();
        	objmapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        	objmapper.registerModule(new JavaTimeModule());
            return objmapper.writeValueAsString(obj);
         } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}