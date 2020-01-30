package com.stackroute.newz.test.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.stackroute.newz.model.News;
import com.stackroute.newz.model.NewsSource;
import com.stackroute.newz.model.Reminder;
import com.stackroute.newz.model.UserNews;
import com.stackroute.newz.repository.NewsRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class NewsRepositoryTest {

    @Autowired
    private NewsRepository newsRepository;

    private News news;
    private NewsSource newsSource;
    private Reminder reminder;
    private UserNews userNews = new UserNews();
    
    private List<News> newsList = null;


    @BeforeEach
    public void setUp() throws Exception {

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
        news.setUrl("//CSKIndiansVcRCB.html");
        news.setUrlToImage("//CSKIndiansVcRCB.png");
        news.setReminder(reminder);
        news.setNewsSource(newsSource);
        
        newsList = new ArrayList<>();
        newsList.add(news);

        userNews.setUserId("Becky123");
        userNews.setNewslist(newsList);
    }

    @AfterEach
    public void tearDown() throws Exception {
    	newsRepository.deleteAll();
    }

    @Test
    public void AddNewsTest() {
    	newsRepository.insert(userNews);
        List<News> allNews = newsRepository.findById("Becky123").get().getNewslist();
        assertThat(newsList.get(0).getNewsId(), is(allNews.get(0).getNewsId()));
    }


    @Test
    public void deleteNewsTest() {
    	newsRepository.insert(userNews);
        List<News> allNews = newsRepository.findById("Becky123").get().getNewslist();
        assertThat(newsList.get(0).getNewsId(), is(allNews.get(0).getNewsId()));
        Iterator<News> iterator = allNews.listIterator();
        while (iterator.hasNext()) {
            news = iterator.next();
            if (news.getNewsId() == 1)
                iterator.remove();
        }

        userNews.setNewslist(allNews);
        newsRepository.save(userNews);

        allNews = newsRepository.findById("Becky123").get().getNewslist();

        assertThat(true,is(allNews.isEmpty()));

    }


    @Test
    public void updateNewsTest() {

    	newsRepository.insert(userNews);
        List<News> allNews = newsRepository.findById("Becky123").get().getNewslist();
        assertThat(newsList.get(0).getNewsId(), is(allNews.get(0).getNewsId()));
        Iterator<News> iterator = allNews.listIterator();
        while (iterator.hasNext()) {
            news = iterator.next();
            if (news.getNewsId() == 1)
                news.setContent("CSK vs RCB match scheduled  for 4 PM is cancelled");
        }
        userNews.setNewslist(allNews);
        newsRepository.save(userNews);
        allNews = newsRepository.findById("Becky123").get().getNewslist();
        assertThat("CSK vs RCB match scheduled  for 4 PM is cancelled", is(allNews.get(0).getContent()));
    }

    @Test
    public void getAllNewsByUserId() {

    	newsRepository.insert(userNews);
        List<News> allNews = newsRepository.findById("Becky123").get().getNewslist();
        assertThat(allNews.size(),is(1));
    }
}