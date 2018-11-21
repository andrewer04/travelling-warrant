package hu.baranyos.service.vehicle;

import java.util.List;

import hu.baranyos.model.entity.Vehicle;

public interface VehicleService {
    public Vehicle getVehicle(String name);

    public void saveVehicle(final Vehicle VehicleDAO);

    public List<Vehicle> getAllVehicle();

    public Vehicle getVehicle(Integer id);

    public void updateSpeedometer(final Vehicle vehicle, int speedometer);
}
