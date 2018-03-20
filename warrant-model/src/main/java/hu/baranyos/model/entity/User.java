package hu.baranyos.model.entity;

import java.util.List;

public class User {

    private Integer id;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String password;
    private List<Travel> travels;
    private List<Fueling> fuelings;

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(final String gender) {
        this.gender = gender;
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
        return firstName + lastName;
    }

}
