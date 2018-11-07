package hu.baranyos.service.travel;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.baranyos.model.entity.Travel;
import hu.baranyos.repository.travel.TravelRepository;
import hu.baranyos.service.user.UserService;

@Service
public class TravelServiceImpl implements TravelService {

    @Autowired
    TravelRepository travelRepository;

    @Autowired
    UserService userService;

    @Override
    @Transactional
    public void saveTravel(final Travel travelDAO) {
        final Travel travel = new Travel();
        travel.setFrom(travelDAO.getFrom());
        travel.setTo(travelDAO.getTo());
        travel.setStart(travelDAO.getStart());
        travel.setEnd(travelDAO.getEnd());
        travel.setDistance(travelDAO.getEnd() - travelDAO.getStart());
        travel.setVehicle(travelDAO.getVehicle());
        travel.setUsers(travelDAO.getUsers());
        travel.setDate(new Date());

        travelRepository.save(travel);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Travel> getAllTravel() {
        return travelRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Travel getTravel(final Integer id) {
        return travelRepository.findById(id).get();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Travel> getByDateAfter(final Date date) {
        if (date == null) {
            return travelRepository.findAll();
        }
        return travelRepository.findByDateAfter(date);
    }

}
