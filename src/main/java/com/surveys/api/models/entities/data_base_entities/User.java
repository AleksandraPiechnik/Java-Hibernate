package com.surveys.api.models.entities.data_base_entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;
    private String username;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user" )
    private Set<Survey> surveySet = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Answer> answerSet = new HashSet<>();


    // getters, setters and constructors


    @Override
    public String toString() {
        return
                "user_id=" + user_id +
                ", username='" + username ;
    }

    public User(String username) {
        this.username = username;
    }

    public User() {
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
