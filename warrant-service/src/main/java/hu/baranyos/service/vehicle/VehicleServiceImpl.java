package hu.baranyos.service.vehicle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.baranyos.model.entity.Vehicle;
import hu.baranyos.repository.vehicle.VehicleRepository;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Vehicle> getAllVehicle() {
        return vehicleRepository.findAll();
    }

    @Override
    public void saveVehicle(final Vehicle VehicleDAO) {
        // TODO Auto-generated method stub

    }

    @Override
    public Vehicle getVehicle(final String name) {
        return vehicleRepository.findByName(name);
    }

}