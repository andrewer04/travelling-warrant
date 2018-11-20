package hu.baranyos.service.location;

import java.util.ArrayList;
import java.util.List;

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

import hu.baranyos.model.entity.Location;
import hu.baranyos.repository.location.LocationRepository;

@RunWith(SpringRunner.class)
public class LocationServiceTest {

    @TestConfiguration
    static class LocationServiceImplTestContextConfiguration {

        @Bean
        public LocationService locationService() {
            return new LocationServiceImpl();
        }
    }

    @Autowired
    private LocationService locationService;

    @MockBean
    private LocationRepository locationRepository;

    List<Location> locationList = new ArrayList<>();
    List<Location> foundList;
    Location location;

    @Before
    public void setUp() {
        location = new Location();
        location.setName("Budapest");
        locationList.add(location);

        location = new Location();
        location.setName("Szeged");
        locationList.add(location);

        Mockito.when(locationRepository.findAll()).thenReturn(locationList);
        Mockito.when(locationRepository.findByName(ArgumentMatchers.anyString()))
                .thenReturn(location);
    }

    @Test
    public void getAllLocationTest() {
        foundList = locationService.getAllLocation();
        Assertions.assertThat(foundList).isEqualTo(locationList);
    }

    @Test
    public void getLocationTest() {
        final Location foundLocation = locationService.getLocation("Szeged");
        Assertions.assertThat(foundLocation).isEqualTo(location);
    }
}
