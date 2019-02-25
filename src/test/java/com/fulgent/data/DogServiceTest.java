package com.fulgent.data;

import com.fulgent.data.repository.entity.DogEntity;
import com.fulgent.data.repository.DogRepository;
import com.fulgent.data.repository.UserFavoritedRepository;
import com.fulgent.data.rest.model.ApplicationMessage;
import com.fulgent.data.rest.model.Dog;
import com.fulgent.data.rest.model.VoteUpDown;
import com.fulgent.data.service.impl.DogServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

// unit testing
@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class DogServiceTest {

    @Autowired
    private TestEntityManager entityManager;

//    @Autowired
//    private DogRepository dogRepository;

    @Autowired
    private DogServiceImpl dogServiceImpl;

    @Autowired
    private UserFavoritedRepository dogFavoriteRepository;

    @Test
    public void contextLoads() {
    }

    //public List<DogEntity> findDogsByBreed(@Param("breed") String breed);
    @Test
    public void whenFindByBreed_thenReturnDogs() {
        // given
        DogEntity dog = new DogEntity("pug", "http://i.imgur.com/ozJD7SC.png","pug desc");
        entityManager.persist(dog);
        entityManager.flush();

        // when
        List<Dog> foundDogsList = dogServiceImpl.findDogsByBreed(dog.getBreed());

        // then
        assertThat(foundDogsList.get(0).getBreed()).isEqualTo(dog.getBreed());
    }

    //public List<DogEntity> findDogsGroupByBreed();
    @Test
    public void whenFindGroupsByBreed_thenReturnDogs() {
        // given
        DogEntity dog1 = new DogEntity("pug", "http://i.imgur.com/ozJD7SC.png","pug desc");
        DogEntity dog2 = new DogEntity("labrador", "http://i.imgur.com/eE29vX4.png","labrador desc");
        entityManager.persist(dog1);
        entityManager.persist(dog2);
        entityManager.flush();

        // when
        List<Dog> foundDogsGroups = dogServiceImpl.findDogsGroupByBreed();

        // then
        assertThat(foundDogsGroups.size()).isEqualTo(2);
    }

    //public DogEntity findDogDetail(@Param("123") long dogId);
    @Test
    public void whenFindDetailById_thenReturnDogs() {
        // given
        DogEntity dog = new DogEntity("pug", "http://i.imgur.com/ozJD7SC.png","TEST");
        long dogId = dog.getDid();
        entityManager.persist(dog);
        entityManager.flush();

        // when
        Dog foundDog = dogServiceImpl.findDogDetail(dogId);

        // then
        assertThat(foundDog.getDescription()).isEqualTo(dog.getDescription());
    }

    //public ApplicationMessage voteUpDown(@RequestBody VoteUpDown voteUpDown);
    @Test
    public void whenVoteUpDown_thenReturnVotes() {
        // given
        DogEntity dog = new DogEntity("pug", "http://i.imgur.com/ozJD7SC.png","TEST");
        long dogId = dog.getDid();
        entityManager.persist(dog);
        entityManager.flush();

        VoteUpDown vote = new VoteUpDown("user123", dogId, Boolean.TRUE);
        // when
        String voteDog = dogServiceImpl.voteUpDown(vote);

        // then
        assertThat(voteDog.equals("success"));
    }
}

