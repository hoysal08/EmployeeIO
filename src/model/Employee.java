package model;

import java.util.*;

public class Employee {

    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private double experience;

    public Employee(String firstName,
                    String lastName,
                    Date dateOfBirth,
                    double experience) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.experience = experience;
    }

    public String getFirstName() {

        return firstName;
    }


    public String getLastName() {

        return lastName;
    }

    public double getExperience() {

        return experience;
    }

    public Date getDateOfBirth() {

        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {

        this.dateOfBirth = dateOfBirth;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }


    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public void setExperience(double experience) {

        this.experience = experience;
    }
}
