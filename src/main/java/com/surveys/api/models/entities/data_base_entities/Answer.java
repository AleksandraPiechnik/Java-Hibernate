package com.surveys.api.models.entities.data_base_entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Answer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer answer_id;
    @ManyToOne
    private Survey survey;
    @ManyToOne
    private User user;

    private Integer rating;


    // getters, setters and constructors
    public Answer(Survey survey, User user, Integer rating) {
        this.survey = survey;
        this.user = user;
        this.rating = rating;
    }

    public Answer() {
    }


    public Integer getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(Integer answer_id) {
        this.answer_id = answer_id;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}