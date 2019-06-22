package com.surveys.api.models.entities.pojo;


import com.surveys.api.models.entities.data_base_entities.User;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SystemStat implements Serializable {
    private Map<User, Integer> Survey_Ranking = new HashMap<>();
    private Map<User, Integer> Answer_Ranking = new HashMap<>();
    private Double Survey_NumberPerUser =0.0;
    private Double Answer_NumberPerUser =0.0;
    private Integer Surveys_Number;

    public SystemStat(Map<User, Integer> surveyRanking, Map<User, Integer> answerRanking, Double surveyNumberPerUser, Double answerNumberPerUser, Integer surveysNumber) {
        Survey_Ranking = surveyRanking;
        Answer_Ranking = answerRanking;
        Survey_NumberPerUser = surveyNumberPerUser;
        Answer_NumberPerUser = answerNumberPerUser;
        Surveys_Number = surveysNumber;
    }

    public SystemStat() {
    }

    public Map<User, Integer> getSurvey_Ranking() {
        return Survey_Ranking;
    }

    public void setSurvey_Ranking(Map<User, Integer> survey_Ranking) {
        Survey_Ranking = survey_Ranking;
    }

    public Map<User, Integer> getAnswer_Ranking() {
        return Answer_Ranking;
    }

    public void setAnswer_Ranking(Map<User, Integer> answer_Ranking) {
        Answer_Ranking = answer_Ranking;
    }

    public Double getSurvey_NumberPerUser() {
        return Survey_NumberPerUser;
    }

    public void setSurvey_NumberPerUser(Double survey_NumberPerUser) {
        Survey_NumberPerUser = survey_NumberPerUser;
    }

    public Double getAnswer_NumberPerUser() {
        return Answer_NumberPerUser;
    }

    public void setAnswer_NumberPerUser(Double answer_NumberPerUser) {
        Answer_NumberPerUser = answer_NumberPerUser;
    }

    public Integer getSurveys_Number() {
        return Surveys_Number;
    }

    public void setSurveys_Number(Integer surveys_Number) {
        Surveys_Number = surveys_Number;
    }
}
