package com.surveys.api.models.services;

import org.springframework.stereotype.Service;


@Service
public class AnswerService {


    public boolean invalidIdFormat(String answer_id){

        try{
            Integer.parseInt(answer_id);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }

    public boolean checkIfInvalidFormatRating(String rating){
        if(rating.equals("0")||rating.equals("1")||rating.equals("2")||rating.equals("3")||rating.equals("4")||rating.equals("5")) return true;
        else return false;
    }


}
