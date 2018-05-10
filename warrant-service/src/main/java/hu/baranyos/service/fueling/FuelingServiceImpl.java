package hu.baranyos.service.fueling;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.baranyos.model.entity.Fueling;
import hu.baranyos.repository.fueling.FuelingRepository;
import hu.baranyos.service.user.UserService;

@Service
public class FuelingServiceImpl implements FuelingService {

    @Autowired
    private FuelingRepository fuelingRepository;

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public void save(final Fueling fuelingDAO) {
        final Fueling fueling = new Fueling();
        fueling.setAmount(fuelingDAO.getAmount());
        fueling.setVehicle(fuelingDAO.getVehicle());

        fueling.setUser(userService.getCurrentUser());
        fueling.setDate(new Date());

        fuelingRepository.save(fueling);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Fueling> getAllFuelings() {
        return fuelingRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Fueling> getByDateAfter(final Date date) {
        if (date == null) {
            return fuelingRepository.findAll();
        }
        return fuelingRepository.findByDateAfter(date);
    }
}
