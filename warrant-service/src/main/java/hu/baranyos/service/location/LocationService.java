package hu.baranyos.service.location;

import java.util.List;

import hu.baranyos.model.entity.Location;

public interface LocationService {
    public List<Location> getAllLocation();

    public void saveLocation(Location locationDAO);

    public Location getLocaton(String name);
}
