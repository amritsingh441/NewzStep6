package com.stackroute.newz.test.service;


import com.stackroute.newz.util.exception.NewsNotFoundException;
import com.stackroute.newz.model.NewsSource;
import com.stackroute.newz.model.News;
import com.stackroute.newz.model.UserNews;
import com.stackroute.newz.model.Reminder;
import com.stackroute.newz.repository.NewsRepository;
import com.stackroute.newz.service.NewsServiceImpl;
import java.util.Optional;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class NewsServiceImplTest {


    private News news;
    private UserNews userNews;
    private NewsSource newsSource;
    private Reminder reminder;
    @Mock
    private NewsRepository newsRepository;
    @InjectMocks
    private NewsServiceImpl newsServiceImpl;
    private List<News> newsList = null;
    Optional<UserNews> options;


    @BeforeEach
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        newsSource = new NewsSource();
        
        newsSource.setNewsSourceId(1);
        newsSource.setNewsSourceName("Cricket-Category");
        newsSource.setNewsSourceDesc("All about IPL");
        newsSource.setNewsSourceCreatedBy("Becky123");
        newsSource.setNewsSourceCreationDate();
        
        reminder = new Reminder();
        reminder.setReminderId("5b0509731764e3096984eae6");
        reminder.setSchedule();

        news = new News();
        news.setNewsId(1);
        news.setTitle("IPLT20 Match - 01");
        news.setAuthor("Becky123");
        news.setDescription("Ipl match 01 - CSK Vs RCB");
        news.setPublishedAt();
        news.setContent("CSK vs RCB match scheduled for 4 PM");
        news.setUrl("//CSKVsRCB.html");
        news.setUrlToImage("//CSKVsRCB.png");
        news.setReminder(reminder);
        news.setNewsSource(newsSource);

        newsList = new ArrayList<>();
        newsList.add(news);

        userNews = new UserNews();

        userNews.setUserId("Becky123");
        userNews.setNewslist(newsList);

        options = Optional.of(userNews);
        
    }


    @Test
    public void addNewsSuccess() {
        when(newsRepository.insert((UserNews) any())).thenReturn(userNews);
        boolean status = newsServiceImpl.addNews(news);
        assertEquals(true, status);
    }

    @Test
    public void addNewsFailure() {

        when(newsRepository.insert(userNews)).thenReturn(null);
        boolean status = newsServiceImpl.addNews(news);
        assertEquals(false, status);
     }


    @Test
    public void deleteNewsSuccess() {
        when(newsRepository.findById(userNews.getUserId())).thenReturn(options);
        when(newsRepository.save(userNews)).thenReturn(userNews);
        boolean flag = newsServiceImpl.deleteNews("Becky123", news.getNewsId());
        assertEquals(true, flag);
    }

    @Test
    public void deleteNewsFailure() {
        when(newsRepository.findById(userNews.getUserId())).thenReturn(null);
        when(newsRepository.save(userNews)).thenReturn(userNews);
        
        assertThrows(
        		NullPointerException.class,
                    () -> { newsServiceImpl.deleteNews("Becky123", news.getNewsId()); });
    
    }


    @Test
    public void deleteAllNewsSuccess() throws NewsNotFoundException {

        when(newsRepository.findById("Becky123")).thenReturn(options);
        when(newsRepository.save(userNews)).thenReturn(userNews);
        boolean flag = newsServiceImpl.deleteAllNews("Becky123");
        assertEquals(true, flag);

    }

    

    @Test
    public void deleteAllNewsFailure() throws NewsNotFoundException {
        when(newsRepository.findById("Becky123")).thenThrow(NoSuchElementException.class);
        
        assertThrows(
        		NewsNotFoundException.class,
                    () -> { newsServiceImpl.deleteAllNews("Becky123"); });
       
    }

    @Test
    public void updateNewsSuccess() throws NewsNotFoundException {

        when(newsRepository.findById("Becky123")).thenReturn(options);
        news.setContent("Match cancelled");
        newsList.add(news);
        News fetchedNews = newsServiceImpl.updateNews(news, news.getNewsId(), news.getAuthor());
        assertEquals(news, fetchedNews);


    }

    @Test
    public void updateNewsFailure() throws NewsNotFoundException {

        when(newsRepository.findById("Becky123")).thenThrow(NoSuchElementException.class);
        news.setContent("Match cancelled");
        newsList.add(news);
        
        assertThrows(
        		NewsNotFoundException.class,
                    () -> { newsServiceImpl.updateNews(news, news.getNewsId(), news.getAuthor()); });

    }

    @Test
    public void getNewsByNewsIdSuccess() throws NewsNotFoundException {
        when(newsRepository.findById("Becky123")).thenReturn(options);
        News fetechedNews = newsServiceImpl.getNewsByNewsId("Becky123", news.getNewsId());
        assertEquals(news, fetechedNews);
    }

    @Test
    public void getNewsByNewsIdFailure() throws NewsNotFoundException {
        when(newsRepository.findById("Becky123")).thenThrow(NoSuchElementException.class);
        
        assertThrows(
        		NewsNotFoundException.class,
                    () -> { newsServiceImpl.getNewsByNewsId("Becky123", news.getNewsId()); });
       
    }

    @Test
    public void getAllNewsByUserId() {
        when(newsRepository.findById("Becky123")).thenReturn(options);
        List<News> newslist1 = newsServiceImpl.getAllNewsByUserId("Becky123");
        assertEquals(newsList, newslist1);
    }
}