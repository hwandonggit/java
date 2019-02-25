package com.fulgent.data;

import com.fulgent.data.repository.DogRepository;
import com.fulgent.data.repository.UserFavoritedRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
//@SpringBootTest(
//        SpringBootTest.WebEnvironment.MOCK,
//        classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")

public class DogServiceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TestEntityManager entityManager;

    @MockBean
    private DogRepository dogRepository;

    @Autowired
    private UserFavoritedRepository dogFavoriteRepository;

    @Test
    public void retrieveDogs_basic() throws Exception {
        this.mockMvc.perform(get("/")).andExpect(status().isOk())
                .andExpect(content().string("Hello World"));
    }

}