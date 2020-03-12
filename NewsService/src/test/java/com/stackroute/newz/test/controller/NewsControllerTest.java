package com.stackroute.newz.test.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.stackroute.newz.controller.NewsController;
import com.stackroute.newz.util.exception.NewsNotFoundException;
import com.stackroute.newz.model.NewsSource;
import com.stackroute.newz.model.News;
import com.stackroute.newz.model.Reminder;
import com.stackroute.newz.service.NewsService;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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
@SpringBootTest
@AutoConfigureMockMvc
class NewsControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private News news;
    @MockBean
    private NewsSource newsSource;
    @MockBean
    private Reminder reminder;
    @MockBean
    private NewsService newsService;

    @InjectMocks
    private NewsController newsController;
    
    private List<News> newsList;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(newsController).build();
        newsSource = new NewsSource();
        
        newsSource.setNewsSourceId(1);
        newsSource.setNewsSourceName("CNN");
        newsSource.setNewsSourceDesc("CNN - US");
        newsSource.setNewsSourceCreatedBy("Becky123");
        newsSource.setNewsSourceCreationDate();

        reminder = new Reminder();
        reminder.setReminderId("5b0509731764e3096984eae6");
        reminder.setSchedule();

        List<Reminder> reminderList = new ArrayList<>();
        reminderList.add(reminder);

        news = new News();
        news.setNewsId(1);
        news.setTitle("IPLT20 Match - 01");
        news.setAuthor("Becky123");
        news.setDescription("Ipl match 01 - CSK Vs RCB");
        news.setPublishedAt();
        news.setContent("CSK vs RCB match scheduled  for 4 PM");
        news.setUrl("//CSKIndiansVcRCB.html");
        news.setUrlToImage("//CSKIndiansVcRCB.png");
        news.setReminder(reminder);
        news.setNewsSource(newsSource);

        newsList = new ArrayList<>();
        newsList.add(news);

    }


    @Test
    public void addNewsSuccess() throws Exception {
        when(newsService.addNews(any())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/news").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(news)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());

    }


    @Test
    public void addNewsFailure() throws Exception {
        when(newsService.addNews(any())).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/news").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(news)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());

    }


    @Test
    public void deleteNewsSuccess() throws Exception {

        when(newsService.deleteNews("Becky123", news.getNewsId())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/news/Becky123/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void deleteNewsFailure() throws Exception {

        when(newsService.deleteNews("Becky123", news.getNewsId())).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/news/Becky123/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void deleteAllNewsSuccess() throws Exception {

        when(newsService.deleteAllNews("Becky123")).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/news/Becky123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void deleteAllNewsFailure() throws Exception {

        when(newsService.deleteAllNews("Becky123")).thenThrow(NewsNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/news/Becky123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());

    }


    @Test
    public void updateNewsSuccess() throws Exception {

        when(newsService.updateNews(any(), eq(news.getNewsId()), eq("Becky123"))).thenReturn(news);
        news.setContent("Mumbai Indians vs RCB match scheduled  for 6 PM");
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/news/Becky123/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(news)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void updateNewsFailure() throws Exception {

        when(newsService.updateNews(any(), eq(news.getNewsId()), eq("Becky123"))).thenThrow(NewsNotFoundException.class);
        news.setContent("Mumbai Indians vs RCB match scheduled  for 6 PM");
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/news/Becky123/" + news.getNewsId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(news)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void getNewsByIdSuccess() throws Exception {

        when(newsService.getNewsByNewsId("Becky123", 1)).thenReturn(news);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/news/Becky123/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void getNewsByIdFailure() throws Exception {

        when(newsService.getNewsByNewsId("Becky123", 1)).thenThrow(NewsNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/news/Becky123/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getAllNewsByUserIdSuccess() throws Exception {
        when(newsService.getAllNewsByUserId("Becky123")).thenReturn(newsList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/news/Becky123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getAllNewsByUserIdFailure() throws Exception {
        when(newsService.getAllNewsByUserId("Becky123")).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/news/Becky123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
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
