package com.dddd.questionnaireportal.database.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="response_per_user")
public class ResponsePerUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @OneToMany(mappedBy = "responsePerUser", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    @OrderBy("id Asc")
    private List<Response> responses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Response> getResponses() {
        return responses;
    }

    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }
}
