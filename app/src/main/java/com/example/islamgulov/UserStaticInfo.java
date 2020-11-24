package com.example.islamgulov;

import java.util.ArrayList;
import java.util.List;

public class UserStaticInfo {
    public final static String POSITION = "position";
    public static List<User> users = new ArrayList<>();

    public UserStaticInfo() {
        if(users.size()==0)
            AddUsersInList();


    }

    private void AddUsersInList() {
        users.add(new User("Данил", "Не выкупает", 18,0));
        users.add(new User("Данил", "Не выкупает", 18,1));
        users.add(new User("Данил", "Не выкупает", 18,2));
        users.add(new User("Данил", "Не выкупает", 18,0));


    }
}
