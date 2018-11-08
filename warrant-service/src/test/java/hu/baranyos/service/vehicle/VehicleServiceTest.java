package hu.baranyos.service.vehicle;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import hu.baranyos.model.entity.Vehicle;
import hu.baranyos.repository.vehicle.VehicleRepository;

@RunWith(SpringRunner.class)
public class VehicleServiceTest {

    @TestConfiguration
    static class VehicleServiceImplTestContextConfiguration {

        @Bean
        public VehicleService vehicleService() {
            return new VehicleServiceImpl();
        }
    }

    @Autowired
    private VehicleService vehicleService;

    @MockBean
    private VehicleRepository vehicleRepository;

    @Before
    public void setUp() {
        final Vehicle vehicle = new Vehicle();
        vehicle.setName("Toyota");
        vehicle.setLicencePlateNumber("abc123");

        Mockito.when(vehicleRepository.findByName(vehicle.getName())).thenReturn(vehicle);
    }

    @Test
    public void getVehicleByNameTest() {
        final String name = "Toyota";
        final Vehicle found = vehicleService.getVehicle(name);

        Assertions.assertThat(found.getName()).isEqualTo(name);
    }

}
