package com.surveys.api.controllers;


import com.surveys.api.models.entities.data_base_entities.Answer;
import com.surveys.api.models.entities.data_base_entities.Survey;
import com.surveys.api.models.entities.data_base_entities.User;
import com.surveys.api.models.entities.pojo.UserStat;
import com.surveys.api.models.exceptions.UserNotExistException;
import com.surveys.api.models.repositories.AnswerRepository;
import com.surveys.api.models.repositories.SurveyRepository;
import com.surveys.api.models.repositories.UserRepository;
import com.surveys.api.models.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private SurveyRepository surveyRepository;
    private UserService userService = new UserService();

    @RequestMapping(value = "/api/user/{username}", method = RequestMethod.POST)
    public User addUser(@PathVariable String username) {
        return userRepository.save(new User(username));
    }

    @RequestMapping(value = "/api/stats/user/{user_id}", method = RequestMethod.GET)
    public UserStat statsUser(@PathVariable String user_id) throws UserNotExistException {
        if (!userService.invalidIdFormat(user_id)) throw new UserNotExistException("There is no user with such ID");
        Optional<User> user = userRepository.findById(Integer.parseInt(user_id));

        List<Survey> surveyList = new ArrayList<>();
        List<Answer> answerList = new ArrayList<>();

        for (Survey survey : surveyRepository.findAll()) {
            if (survey.getUser().getUser_id().equals(Integer.parseInt(user_id))) surveyList.add(survey);
        }
        for (Answer answer : answerRepository.findAll()) {
            if (answer.getUser().getUser_id().equals(Integer.parseInt(user_id))) answerList.add(answer);
        }

        Integer userAnswersQuantity = answerList.size();
        Double averageRating = 0.0;
        for (int i = 0; i < answerList.size(); i++) {
            averageRating += answerList.get(i).getRating();
        }
        averageRating /= userAnswersQuantity;


        UserStat userStat = new UserStat(user.get().getUsername(),user.get().getUser_id(),surveyList.size(), averageRating, answerList.size());
        return userStat;
    }


}
