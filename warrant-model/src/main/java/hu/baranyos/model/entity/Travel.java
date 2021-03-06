package hu.baranyos.model.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TRAVEL")
public class Travel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int startKilometer;
    private int endKilometer;
    private int distance;

    @ManyToOne
    @JoinColumn(name = "start_location")
    private Location startLocation;

    @ManyToOne
    @JoinColumn(name = "end_location")
    private Location endLocation;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USERS_TRAVELS",
            joinColumns = @JoinColumn(name = "travel_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<User> users;

    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(final Set<User> users) {
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
