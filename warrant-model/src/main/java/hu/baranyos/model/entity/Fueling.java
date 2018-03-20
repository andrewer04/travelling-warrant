package hu.baranyos.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FUELING")
public class Fueling {

    @Id
    @GeneratedValue
    private int id;

    private int amount;

    @ManyToOne
    @JoinColumn(name = "id")
    private User user;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "id")
    private Vehicle vehicle;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(final int amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(final Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return user + ": " + amount + vehicle;
    }
}
