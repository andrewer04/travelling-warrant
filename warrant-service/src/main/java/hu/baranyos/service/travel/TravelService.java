package hu.baranyos.service.travel;

import java.util.Date;
import java.util.List;

import hu.baranyos.model.entity.Travel;

public interface TravelService {

    public void saveTravel(Travel travelDAO);

    public List<Travel> getAllTravel();

    public Travel getTravel(Integer id);

    public List<Travel> getByDateAfter(final Date date);

}
