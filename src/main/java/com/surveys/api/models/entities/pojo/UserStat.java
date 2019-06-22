package com.surveys.api.models.entities.pojo;


import java.io.Serializable;

public class UserStat implements Serializable {
    String username;
    Integer user_id;
    int NumberOfSurveys;
    Double SurveysAvgRating;
    Integer AnswerQuantity;

    public UserStat() {
    }

    public UserStat(String username, Integer user_id, int numberOfSurveys, Double surveysAvgRating, Integer answerQuantity) {
        this.username = username;
        this.user_id = user_id;
        NumberOfSurveys = numberOfSurveys;
        SurveysAvgRating = surveysAvgRating;
        AnswerQuantity = answerQuantity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public int getNumberOfSurveys() {
        return NumberOfSurveys;
    }

    public void setNumberOfSurveys(int numberOfSurveys) {
        NumberOfSurveys = numberOfSurveys;
    }

    public Double getSurveysAvgRating() {
        return SurveysAvgRating;
    }

    public void setSurveysAvgRating(Double surveysAvgRating) {
        SurveysAvgRating = surveysAvgRating;
    }

    public Integer getAnswerQuantity() {
        return AnswerQuantity;
    }

    public void setAnswerQuantity(Integer answerQuantity) {
        AnswerQuantity = answerQuantity;
    }
}
