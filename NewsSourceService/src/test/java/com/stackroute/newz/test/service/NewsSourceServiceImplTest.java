package com.stackroute.newz.test.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.stackroute.newz.model.NewsSource;
import com.stackroute.newz.repository.NewsSourceRepository;
import com.stackroute.newz.service.NewsSourceServiceImpl;
import com.stackroute.newz.util.exception.NewsSourceNotFoundException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class NewsSourceServiceImplTest {

	@MockBean
    private NewsSource newsSource;
    @Mock
    private NewsSourceRepository newsSourceRepository;
    @InjectMocks
    private NewsSourceServiceImpl newsSourceServiceImpl;
    private List<NewsSource> allNewssource = null;
    Optional<NewsSource> options;

    @BeforeEach
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        newsSource = new NewsSource();

        newsSource.setNewsSourceId(1);
        newsSource.setNewsSourceName("Cricket-Category");
        newsSource.setNewsSourceDesc("All about Cricket");
        newsSource.setNewsSourceCreatedBy("Becky123");
        newsSource.setNewsSourceCreationDate();

        allNewssource = new ArrayList<>();
        allNewssource.add(newsSource);
        options = Optional.of(newsSource);
    }

    @Test
    public void createNewssourceTestSuccess() throws Exception {

        when(newsSourceRepository.insert(newsSource)).thenReturn(newsSource);
        boolean savedNewssource = newsSourceServiceImpl.addNewsSource(newsSource);
        assertThat(savedNewssource,is(true));


    }
    
    @Test
    public void createNewssourceTestFailure() throws Exception {

        when(newsSourceRepository.insert(newsSource)).thenReturn(null);
        boolean savedNewssource = newsSourceServiceImpl.addNewsSource(newsSource);
        assertThat(savedNewssource, is(false));

    }

    @Test
    public void deleteNewssourceSuccess() throws Exception {
        when(newsSourceRepository.findById(newsSource.getNewsSourceId())).thenReturn(options);
        when(newsSourceRepository.save(newsSource)).thenReturn(newsSource);
        boolean flag = newsSourceServiceImpl.deleteNewsSource(1);
        assertThat(true, is(flag));
    }


    @Test
    public void deleteNewssourceFailure() throws Exception {
        when(newsSourceRepository.findById(newsSource.getNewsSourceId())).thenReturn(null);
        when(newsSourceRepository.save(newsSource)).thenReturn(newsSource);
        boolean flag = newsSourceServiceImpl.deleteNewsSource(newsSource.getNewsSourceId());
        assertThat(false, is(flag));
    }


    @Test
    public void updateNewssourceTestSuccess() throws NewsSourceNotFoundException {
        when(newsSourceRepository.findById(newsSource.getNewsSourceId())).thenReturn(options);
        newsSource.setNewsSourceDesc("All about cricket and other sports");
        NewsSource fetchedNewssource = newsSourceServiceImpl.updateNewsSource(newsSource, newsSource.getNewsSourceId());
        assertThat(fetchedNewssource, is(newsSource));

    }

    @Test
    public void updateNewssourceTestFailure() throws NewsSourceNotFoundException {
        when(newsSourceRepository.findById(newsSource.getNewsSourceId())).thenReturn(options);
        newsSource.setNewsSourceDesc("All about cricket and other sports");
        NewsSource fetchedNewssource = newsSourceServiceImpl.updateNewsSource(newsSource, newsSource.getNewsSourceId());
        assertThat(fetchedNewssource, is(newsSource));

    }


    @Test
    public void getNewssourceByIdTestSuccess() throws NewsSourceNotFoundException {

        when(newsSourceRepository.findAllNewsSourceByNewsSourceCreatedBy("Becky123")).thenReturn(allNewssource);
        NewsSource fetchedNewssource = newsSourceServiceImpl.getNewsSourceById("Becky123",1);
        System.out.println("Orange "+allNewssource);
        assertNotNull(fetchedNewssource);

        
    }

    @Test
    public void getNewssourceByIdTestFailure() throws NewsSourceNotFoundException {
    	when(newsSourceRepository.findAllNewsSourceByNewsSourceCreatedBy("Becky123")).thenThrow(NoSuchElementException.class);
    	NewsSource fetchedNewssource = newsSourceServiceImpl.getNewsSourceById(newsSource.getNewsSourceCreatedBy(),newsSource.getNewsSourceId());
        assertNull(fetchedNewssource);

    }


    @Test
    public void getAllNewssourceByUserIdTestSuccess() {
    	String createdBy = newsSource.getNewsSourceCreatedBy();
        when(newsSourceRepository.findAllNewsSourceByNewsSourceCreatedBy(createdBy)).thenReturn(allNewssource);
        
        List<NewsSource> fetchedNewssource = newsSourceServiceImpl.getAllNewsSourceByUserId("Becky123");
        assertThat(fetchedNewssource, is(allNewssource));

    }
}