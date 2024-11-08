package com.example.lab_05.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.Instant;

@Entity
public class Candidate {
    @Id
    private int id;
    private String lastName;
    private String middleName;
    private String firstName;
    private Instant dob;
    private String email;
    private String address;
    private String phone;

    public Candidate() {
    }

    public Candidate(int id, String lastName, String middleName, String firstName, Instant dob, String email, String address, String phone) {
        this.id = id;
        this.lastName = lastName;
        this.middleName = middleName;
        this.firstName = firstName;
        this.dob = dob;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Instant getDob() {
        return dob;
    }

    public void setDob(Instant dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
