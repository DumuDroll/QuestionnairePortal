package com.dddd.questionnaireportal.database.entity;

import javax.persistence.*;
import java.util.UUID;

//@Entity
//@Table(name="user_activation")
public class UserActivation {

  //  @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //@OneToOne(cascade = CascadeType.ALL)
    private User user;
    private UUID uuid;
}
