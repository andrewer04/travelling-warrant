package hu.baranyos.model.entity;

import java.util.List;

public class Vehicle {
    private int id;
    private String name;
    private double consumption;
    private String licencePlateNumber;
    private List<Travel> travels;
    private List<Fueling> fuelings;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(final double consumption) {
        this.consumption = consumption;
    }

    public String getLicencePlateNumber() {
        return licencePlateNumber;
    }

    public void setLicencePlateNumber(final String licencePlateNumber) {
        this.licencePlateNumber = licencePlateNumber;
    }

    public List<Travel> getTravels() {
        return travels;
    }

    public void setTravels(final List<Travel> travels) {
        this.travels = travels;
    }

    public List<Fueling> getFuelings() {
        return fuelings;
    }

    public void setFuelings(final List<Fueling> fuelings) {
        this.fuelings = fuelings;
    }

    @Override
    public String toString() {
        return name + "-" + licencePlateNumber;
    }
}
