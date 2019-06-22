package com.surveys.api.models.services;

import org.springframework.stereotype.Service;


@Service
public class SurveyService {


   public boolean invalidIdFormat(String survey_id){

       try{
           Integer.parseInt(survey_id);
           return true;
       }
       catch (NumberFormatException e){
           return false;
       }
   }



}
