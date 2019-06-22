package com.surveys.api.models.services;

import org.springframework.stereotype.Service;


@Service
public class UserService {


    public boolean invalidIdFormat(String user_id) {

        try {
            Integer.parseInt(user_id);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
