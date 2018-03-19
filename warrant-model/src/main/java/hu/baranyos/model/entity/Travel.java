package hu.baranyos.model.entity;

import java.util.Date;
import java.util.List;

public class Travel {
    private int id;
    private int start;
    private int end;
    private int distance;
    private Location from;
    private Location to;
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
        return start;
    }

    public void setStart(final int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(final int end) {
        this.end = end;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(final int distance) {
        this.distance = distance;
    }

    public Location getFrom() {
        return from;
    }

    public void setFrom(final Location from) {
        this.from = from;
    }

    public Location getTo() {
        return to;
    }

    public void setTo(final Location to) {
        this.to = to;
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
