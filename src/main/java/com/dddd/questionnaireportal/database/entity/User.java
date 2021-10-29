package com.dddd.questionnaireportal.database.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private boolean isActive;
    private String phoneNumber;
    private byte[] salt;
    private String confirmationHash;
    private Date expirationDate;

    @OneToOne(mappedBy = "user")
    @JoinColumn(
            name="id", unique=true, nullable=false, updatable=false)
    private UserActivation userActivation;

    public User(String email, String password, String firstName, String lastName, boolean isActive,
                String phoneNumber, Date expirationDate) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
        this.phoneNumber = phoneNumber;
        this.expirationDate=expirationDate;
    }

    public User() {

    }

    public String getName(){
        return firstName + " " + firstName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getConfirmationHash() {
        return confirmationHash;
    }

    public void setConfirmationHash(String confirmationHash) {
        this.confirmationHash = confirmationHash;
    }

    public UserActivation getUserActivation() {
        return userActivation;
    }

    public void setUserActivation(UserActivation userActivation) {
        this.userActivation = userActivation;
    }
}
