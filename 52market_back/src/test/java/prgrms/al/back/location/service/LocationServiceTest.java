package prgrms.al.back.location.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prgrms.al.back.location.domain.Location;
import prgrms.al.back.location.domain.NearAreaStepOne;
import prgrms.al.back.location.repository.LocationRepository;
import prgrms.al.back.location.repository.NearAreaStepOneRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.transaction.Transaction;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

@Slf4j
@Service
@SpringBootTest
class LocationServiceTest {

    @Autowired
    LocationService service;

    @Autowired
    LocationRepository repository;

    @Autowired
    NearAreaStepOneRepository oneRepository;
    
    @Test
    @Transactional
    public void test(){
        //given
        Location location1 = Location.builder()
                .name("abc")
                .n(12.0)
                .e(24.0)
                .build();

        Location location2 = Location.builder()
                .name("bbc")
                .n(20.0)
                .e(30.0)
                .build();
        
        repository.save(location1);
        repository.save(location2);

        NearAreaStepOne one = NearAreaStepOne.builder()
                .start(location1)
                .end(location2)
                .build();

        oneRepository.save(one);

        //when
        List<Location> nears = service.findNearLocationStepOne(location1);

        //then
        assertAll(
                () -> assertThat(nears.isEmpty(), is(false)),
                () -> assertThat(nears.get(0).getName(), is(location2.getName()))
        );
        System.out.println(nears.get(0).getName());

    }

}