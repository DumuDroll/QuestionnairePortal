package com.dddd.questionnaireportal.database.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="user_activation")
public class UserActivation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private User user;

    private String uuid;
    private Date confirmationExpireDate;
    private Date passChangeExpireDate;
    private String newPass;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getConfirmationExpireDate() {
        return confirmationExpireDate;
    }

    public void setConfirmationExpireDate(Date confirmationExpireDate) {
        this.confirmationExpireDate = confirmationExpireDate;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public Date getPassChangeExpireDate() {
        return passChangeExpireDate;
    }

    public void setPassChangeExpireDate(Date passChangeExpireDate) {
        this.passChangeExpireDate = passChangeExpireDate;
    }
}