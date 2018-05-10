package hu.baranyos.model.entity;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "REPORT")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToMany
    @JoinTable(
            name = "USERS_REPORTS",
            joinColumns = @JoinColumn(name = "report_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<User> users;

    private int distanceSum;
    private int fuelingSum;

    @ElementCollection
    private Map<User, Integer> userKm;

    @ElementCollection
    private Map<User, Integer> userFuelings;

    @ElementCollection
    private Map<User, Integer> userBalance;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(final Set<User> users) {
        this.users = users;
    }

    public int getDistanceSum() {
        return distanceSum;
    }

    public void setDistanceSum(final int distanceSum) {
        this.distanceSum = distanceSum;
    }

    public Map<User, Integer> getUserKm() {
        return userKm;
    }

    public void setUserKm(final Map<User, Integer> userKm) {
        this.userKm = userKm;
    }

    public Map<User, Integer> getUserFuelings() {
        return userFuelings;
    }

    public void setUserFuelings(final Map<User, Integer> userFuelings) {
        this.userFuelings = userFuelings;
    }

    public Map<User, Integer> getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(final Map<User, Integer> userBalance) {
        this.userBalance = userBalance;
    }

    public int getFuelingSum() {
        return fuelingSum;
    }

    public void setFuelingSum(final int fuelingSum) {
        this.fuelingSum = fuelingSum;
    }

}
