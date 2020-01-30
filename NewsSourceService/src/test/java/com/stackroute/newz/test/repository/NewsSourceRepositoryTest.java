package com.stackroute.newz.test.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.stackroute.newz.model.NewsSource;
import com.stackroute.newz.repository.NewsSourceRepository;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class NewsSourceRepositoryTest {

    @Autowired
    private NewsSourceRepository newsSourceRepository;
    private NewsSource newsSource;

    @BeforeEach
    public void setUp() throws Exception {
    	newsSource = new NewsSource();
    	newsSource.setNewsSourceId(1);
    	newsSource.setNewsSourceName("Cricket-Category");
    	newsSource.setNewsSourceDesc("All about Cricket");
    	newsSource.setNewsSourceCreatedBy("Jhon123");
    	newsSource.setNewsSourceCreationDate();
    }

    @AfterEach
    public void tearDown() throws Exception {

    	newsSourceRepository.deleteAll();
    }

    @Test
    public void createNewssourceTest() {

    	newsSourceRepository.insert(newsSource);
    	NewsSource fetchedNewssource = newsSourceRepository.findById(newsSource.getNewsSourceId()).get();
        assertThat(1, is(fetchedNewssource.getNewsSourceId()));

    }

    @Test
    public void deleteNewssourcetest() {

    	newsSourceRepository.insert(newsSource);
    	NewsSource fetchedNewssource = newsSourceRepository.findById(newsSource.getNewsSourceId()).get();
        assertThat(1, is(fetchedNewssource.getNewsSourceId()));
        newsSourceRepository.delete(fetchedNewssource);

    }

    @Test
    public void updateNewssourceTest() {

    	newsSourceRepository.insert(newsSource);
    	NewsSource fetchedNewssource = newsSourceRepository.findById(newsSource.getNewsSourceId()).get();
        assertThat(1, is(fetchedNewssource.getNewsSourceId()));
        fetchedNewssource.setNewsSourceDesc("All about T-20");
        newsSourceRepository.save(fetchedNewssource);
        fetchedNewssource = newsSourceRepository.findById(newsSource.getNewsSourceId()).get();
        assertThat("All about T-20", is(fetchedNewssource.getNewsSourceDesc()));


    }

    @Test
    public void getNewssourceByIdTest() {

    	newsSourceRepository.insert(newsSource);
    	NewsSource fetchedNewssource = newsSourceRepository.findById(newsSource.getNewsSourceId()).get();
        assertThat(1, is(fetchedNewssource.getNewsSourceId()));
    }

    @Test
    public void getAllNewssourceByUserId() {

    	newsSourceRepository.insert(newsSource);

    	newsSource.setNewsSourceId(2);
    	newsSource.setNewsSourceName("Badminton-Category");
    	newsSource.setNewsSourceDesc("All about Badminton");
    	newsSource.setNewsSourceCreatedBy("Jhon123");
    	newsSource.setNewsSourceCreationDate();

    	newsSourceRepository.insert(newsSource);

        List<NewsSource> newssources = newsSourceRepository.findAllNewsSourceByNewsSourceCreatedBy("Jhon123");
        assertThat(newssources.size(), is(2));


    }

}
