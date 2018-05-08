package hu.baranyos.service.location;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.baranyos.model.entity.Location;
import hu.baranyos.repository.location.LocationRepository;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Location> getAllLocation() {
        return locationRepository.findAll();
    }

    @Override
    @Transactional
    public void saveLocation(final Location locationDAO) {
        final Location location = new Location();
        location.setName(locationDAO.getName());

        locationRepository.save(location);

    }

    @Override
    @Transactional(readOnly = true)
    public Location getLocaton(final String name) {
        return locationRepository.findByName(name);
    }

}
