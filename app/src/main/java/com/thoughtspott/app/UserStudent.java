package com.thoughtspott.app;

import com.google.firebase.firestore.auth.User;

public class UserStudent {

    private static volatile UserStudent instance =
            new UserStudent();

    private UserStudent(){}

    public static UserStudent getInstance(){
        return instance;
    }

}
