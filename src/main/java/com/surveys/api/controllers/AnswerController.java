package com.surveys.api.controllers;


import com.surveys.api.models.entities.data_base_entities.Answer;
import com.surveys.api.models.entities.data_base_entities.Survey;
import com.surveys.api.models.entities.data_base_entities.User;
import com.surveys.api.models.exceptions.AnswerNotExistException;
import com.surveys.api.models.exceptions.RatingFormatException;
import com.surveys.api.models.exceptions.SurveyNotExistException;
import com.surveys.api.models.exceptions.UserNotExistException;
import com.surveys.api.models.repositories.AnswerRepository;
import com.surveys.api.models.repositories.SurveyRepository;
import com.surveys.api.models.repositories.UserRepository;
import com.surveys.api.models.services.AnswerService;
import com.surveys.api.models.services.SurveyService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;

@RestController
public class AnswerController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private SurveyRepository surveyRepository;

    private AnswerService answerService = new AnswerService();
    private SurveyService surveyService = new SurveyService();


    @RequestMapping(value = "/api/answer/{survey_id}/{user_id}/{rating}", method = RequestMethod.POST)
    public Answer addAnswer(@PathVariable String survey_id, @PathVariable String user_id, @PathVariable String rating) throws DataFormatException, SurveyNotExistException, RatingFormatException, UserNotExistException {

        if (!surveyService.invalidIdFormat(survey_id))
            throw new DataFormatException("Survey id should be integer number");
        if (!surveyRepository.existsById(Integer.parseInt(survey_id)))
            throw new SurveyNotExistException("There is no survey with such ID");
        if (Integer.parseInt(survey_id) < 0)
            throw new DataFormatException("Survey id should be integer, positive number");
        if (!answerService.checkIfInvalidFormatRating(rating))
            throw new RatingFormatException("Rating has to be number from [0,5]");
        if (!userRepository.existsById(Integer.parseInt(user_id)))
            throw new UserNotExistException("There is no user with such ID");


        Optional<User> user = userRepository.findById(Integer.parseInt(user_id));
        Optional<Survey> survey = surveyRepository.findById(Integer.parseInt(survey_id));
        Answer newAnswer = new Answer(survey.get(), user.get(), Integer.parseInt(rating));
        return answerRepository.save(newAnswer);

    }

    @RequestMapping(value = "/api/answers", method = RequestMethod.GET)
    public String getAllAnswers() {

        JSONObject object = new JSONObject();
        List<Answer> answers = new ArrayList<>();
        answerRepository.findAll().forEach(answers::add);
        object.put("surveys", answers);

        return object.toString();
    }

    @RequestMapping(value = "/api/answer/{answer_id}", method = RequestMethod.GET)
    public Answer getAnswer(@PathVariable String answer_id) throws AnswerNotExistException {

        if (!answerService.invalidIdFormat(answer_id))
            throw new AnswerNotExistException("There is no answer with such ID");
        Optional<Answer> answer = answerRepository.findById(Integer.parseInt(answer_id));
        return answer.get();
    }

    @RequestMapping(value = "/api/answer/{answer_id}/{rating}", method = RequestMethod.PUT)
    public Answer updateAnswer(@PathVariable String answer_id, @PathVariable String rating) throws RatingFormatException, AnswerNotExistException {

        if (!answerService.checkIfInvalidFormatRating(rating))
            throw new RatingFormatException("Rating has to be number from [0,5]");
        if (!answerService.invalidIdFormat(answer_id))
            throw new AnswerNotExistException("There is no answer with such ID");


        Optional<Answer> answer = answerRepository.findById(Integer.parseInt(answer_id));
        answer.get().setRating(Integer.parseInt(rating));
        return answer.get();
    }
}
