package com.surveys.api.controllers;


import com.surveys.api.models.entities.data_base_entities.Answer;
import com.surveys.api.models.entities.data_base_entities.Survey;
import com.surveys.api.models.entities.data_base_entities.User;
import com.surveys.api.models.entities.pojo.SystemStat;
import com.surveys.api.models.exceptions.SurveyNotExistException;
import com.surveys.api.models.exceptions.UserNotExistException;
import com.surveys.api.models.repositories.AnswerRepository;
import com.surveys.api.models.repositories.SurveyRepository;
import com.surveys.api.models.repositories.UserRepository;
import com.surveys.api.models.services.SurveyService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.zip.DataFormatException;

@RestController
public class SurveyController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private SurveyRepository surveyRepository;

    private SurveyService surveyService = new SurveyService();

    @RequestMapping(value = "/api/survey/{user_id}/{survey_title}/{question}", method = RequestMethod.POST)
    public Survey addSurvey(@PathVariable String user_id, @PathVariable String survey_title, @PathVariable String question) throws UserNotExistException {
        int userId = Integer.parseInt(user_id);
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            Survey newSurvey = new Survey(survey_title, question, user.get());
            return surveyRepository.save(newSurvey);
        } else throw new UserNotExistException("There is no user with such ID");
    }

    @RequestMapping(value = "/api/surveys", method = RequestMethod.GET)
    public String getAllSurveys() {
        JSONObject object = new JSONObject();
        object.put("surveys", (List) surveyRepository.findAll());
        return object.toString();
    }

    @RequestMapping(value = "/api/surveys/user/{user_id}", method = RequestMethod.GET)
    public String getUserSurveys(@PathVariable String user_id) throws UserNotExistException {

        JSONObject object = new JSONObject();
        Optional<User> user = userRepository.findById(Integer.parseInt(user_id));

        if (!user.isPresent()) throw new UserNotExistException("There is no user with such ID");
        List<Survey> userSurveyList = new ArrayList<>();
        for (Survey survey : surveyRepository.findAll()) {
            if (survey.getUser().getUser_id().equals(Integer.parseInt(user_id))) userSurveyList.add(survey);

        }

        object.put("user_id", user.get().getUser_id());
        object.put("surveys", userSurveyList);

        return object.toString();
    }

    @RequestMapping(value = "/api/survey/{survey_id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteSurvey(@PathVariable String survey_id) throws DataFormatException, SurveyNotExistException {

        if (!surveyService.invalidIdFormat(survey_id))
            throw new DataFormatException("Survey id should be integer number");
        if (!surveyRepository.existsById(Integer.parseInt(survey_id)))
            throw new SurveyNotExistException("There is no survey with such ID");
        if (Integer.parseInt(survey_id) < 0)
            throw new DataFormatException("Survey id should be integer, positive number");

        surveyRepository.deleteById(Integer.parseInt(survey_id));

    }

    @RequestMapping(value = "/api/stats", method = RequestMethod.GET)
    public SystemStat getSystemStats() {
        List<Survey> surveyList = (List) surveyRepository.findAll();
        List<Answer> answerList = (List) answerRepository.findAll();
        List<User> userList = (List) userRepository.findAll();

        Map<User, Integer> surveyRanking = new HashMap();
        Map<User, Integer> answerRanking = new HashMap();
        for (int i = 0; i < userList.size(); i++) {

            List<Survey> userSurveys = new ArrayList<>();
            for (Survey survey : surveyRepository.findAll()) {
                if (survey.getUser().getUser_id().equals(userList.get(i).getUser_id())) userSurveys.add(survey);
            }
            surveyRanking.put(userList.get(i), userSurveys.size());
            List<Answer> userAnswers= new ArrayList<>();
            for (Answer answer : answerRepository.findAll()) {
                if (answer.getUser().getUser_id().equals(userList.get(i).getUser_id())) userAnswers.add(answer);
            }
            answerRanking.put(userList.get(i), userAnswers.size());

        }
        Double surveyNumberPerUser=Double.valueOf(surveyList.size())/Double.valueOf(userList.size());
        Double answerNumberPerUser=Double.valueOf(answerList.size())/Double.valueOf(userList.size());

        SystemStat systemStat=new SystemStat(surveyRanking,answerRanking,surveyNumberPerUser,answerNumberPerUser,surveyList.size());
        return systemStat;
    }
}
