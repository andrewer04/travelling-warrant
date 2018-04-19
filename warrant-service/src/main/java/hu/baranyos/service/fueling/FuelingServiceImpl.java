package hu.baranyos.service.fueling;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.baranyos.model.entity.Fueling;
import hu.baranyos.repository.fueling.FuelingRepository;

@Service
public class FuelingServiceImpl implements FuelingService {

    @Autowired
    private FuelingRepository fuelingRepository;

    @Override
    public void save(final Fueling fuelingDAO) {
        final Fueling fueling = new Fueling();
        fueling.setAmount(fuelingDAO.getAmount());
        fueling.setUser(fuelingDAO.getUser());
        fueling.setDate(fuelingDAO.getDate());
        fueling.setVehicle(fuelingDAO.getVehicle());

        fuelingRepository.save(fueling);
    }

    @Override
    public List<Fueling> getAllFuelings() {
        return fuelingRepository.findAll();
    }

}
