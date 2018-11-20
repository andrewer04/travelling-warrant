package hu.baranyos.service.fueling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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

import hu.baranyos.model.entity.Fueling;
import hu.baranyos.repository.fueling.FuelingRepository;
import hu.baranyos.service.user.UserService;

@RunWith(SpringRunner.class)
public class FuelingServiceTest {

    @TestConfiguration
    static class FuelingServiceImplTestContextConfiguration {

        @Bean
        public FuelingService fuelingService() {
            return new FuelingServiceImpl();
        }
    }

    @Autowired
    private FuelingService fuelingService;

    @MockBean
    private UserService userService;

    @MockBean
    private FuelingRepository fuelingRepository;

    List<Fueling> fuelingList = new ArrayList<>();
    List<Fueling> foundList;
    Calendar cal = Calendar.getInstance();
    Fueling fueling;

    @Before
    public void setUp() {
        fueling = new Fueling();
        fueling.setAmount(3000);
        cal.set(2018, 10, 11);
        fueling.setDate(cal.getTime());
        fuelingList.add(fueling);

        fueling = new Fueling();
        fueling.setAmount(4000);
        cal.set(2018, 11, 10);
        fueling.setDate(cal.getTime());
        fuelingList.add(fueling);

        Mockito.when(fuelingRepository.findAll()).thenReturn(fuelingList);
        Mockito.when(fuelingRepository.findByDateAfter(ArgumentMatchers.any(Date.class)))
                .thenReturn(new ArrayList<>(Arrays.asList(fueling)));
    }

    @Test
    public void getAllFuelingsTest() {
        foundList = fuelingService.getAllFuelings();
        Assertions.assertThat(foundList).isEqualTo(fuelingList);
    }

    @Test
    public void getByDateAfterTest() {
        foundList = fuelingService.getByDateAfter(cal.getTime());
        Assertions.assertThat(foundList).isEqualTo(new ArrayList<>(Arrays.asList(fueling)));
    }
}
