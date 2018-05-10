package hu.baranyos.service.fueling;

import java.util.Date;
import java.util.List;

import hu.baranyos.model.entity.Fueling;

public interface FuelingService {
    public void save(Fueling fueling);

    public List<Fueling> getAllFuelings();

    public List<Fueling> getByDateAfter(Date date);
}
