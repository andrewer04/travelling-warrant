package hu.baranyos.model.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TRAVEL")
public class Travel {

    @Id
    @GeneratedValue
    private int id;

    private int startKilometer;
    private int endKilometer;
    private int distance;

    @ManyToOne
    @JoinColumn(name = "id")
    private Location startLocation;

    @ManyToOne
    @JoinColumn(name = "id")
    private Location endLocation;

    @ManyToOne
    @JoinColumn(name = "id")
    private Vehicle vehicle;

    private List<User> users;

    private Date date;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getStart() {
        return startKilometer;
    }

    public void setStart(final int start) {
        startKilometer = start;
    }

    public int getEnd() {
        return endKilometer;
    }

    public void setEnd(final int end) {
        endKilometer = end;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(final int distance) {
        this.distance = distance;
    }

    public Location getFrom() {
        return startLocation;
    }

    public void setFrom(final Location from) {
        startLocation = from;
    }

    public Location getTo() {
        return endLocation;
    }

    public void setTo(final Location to) {
        endLocation = to;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(final Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(final List<User> users) {
        this.users = users;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    @Override
    public String toString() {

        return users + ": " + date + distance;
    }

}
