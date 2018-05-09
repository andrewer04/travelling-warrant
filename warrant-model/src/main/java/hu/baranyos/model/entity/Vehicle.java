package hu.baranyos.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "VEHICLE")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String name;

    private double consumption;

    @Column(unique = true)
    private String licencePlateNumber;

    @OneToMany(mappedBy = "vehicle")
    private List<Travel> travels;

    @OneToMany(mappedBy = "vehicle")
    private List<Fueling> fuelings;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
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
