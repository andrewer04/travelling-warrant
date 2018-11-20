package hu.baranyos.service.travel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import hu.baranyos.model.entity.Travel;
import hu.baranyos.repository.travel.TravelRepository;
import hu.baranyos.service.user.UserService;

@RunWith(SpringRunner.class)
public class TravelServiceTest {

    @TestConfiguration
    static class TravelServiceImplTestContextConfiguration {

        @Bean
        public TravelService travelService() {
            return new TravelServiceImpl();
        }
    }

    @Autowired
    private TravelService travelService;

    @MockBean
    TravelRepository travelRepository;

    @MockBean
    private UserService userService;

    List<Travel> travelList = new ArrayList<>();
    List<Travel> foundList;
    Travel travel;
    Calendar cal = Calendar.getInstance();

    @Before
    public void setUp() {
        travel = new Travel();
        travel.setId(1);
        cal.set(2018, 9, 22);
        travel.setDate(cal.getTime());
        travelList.add(travel);

        travel = new Travel();
        travel.setId(2);
        cal.set(2018, 10, 22);
        travel.setDate(cal.getTime());
        travelList.add(travel);

        Mockito.when(travelRepository.findAll()).thenReturn(travelList);
        Mockito.when(travelRepository.findById(ArgumentMatchers.anyInt()))
                .thenReturn(Optional.of(travel));
        Mockito.when(travelRepository.findByDateAfter(ArgumentMatchers.any(Date.class)))
                .thenReturn(new ArrayList<>(Arrays.asList(travel)));
    }

    @Test
    public void getAllTravelTest() {
        foundList = travelService.getAllTravel();
        Assertions.assertThat(foundList).isEqualTo(travelList);
    }

    @Test
    public void getTravelTest() {
        final Travel foundTravel = travelService.getTravel(2);
        Assertions.assertThat(foundTravel).isEqualTo(travel);
    }

    @Test
    public void getByDateAfterTest() {
        foundList = travelService.getByDateAfter(cal.getTime());
        Assertions.assertThat(foundList).isEqualTo(new ArrayList<>(Arrays.asList(travel)));
    }
}
