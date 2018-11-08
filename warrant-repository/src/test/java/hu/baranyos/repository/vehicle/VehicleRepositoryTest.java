package hu.baranyos.repository.vehicle;

import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import hu.baranyos.model.entity.Vehicle;

@Ignore
@RunWith(SpringRunner.class)
@DataJpaTest
public class VehicleRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Test
    public void findByNameTest() {
        final Vehicle vehicle = new Vehicle();
        vehicle.setName("Toyota");
        vehicle.setLicencePlateNumber("abc123");

        entityManager.persist(vehicle);
        entityManager.flush();

        final Vehicle found = vehicleRepository.findByName(vehicle.getName());

        Assertions.assertThat(found.getLicencePlateNumber())
                .isEqualTo(vehicle.getLicencePlateNumber());
    }
}
