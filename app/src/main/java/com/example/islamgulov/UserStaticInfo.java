package com.example.islamgulov;

import java.util.ArrayList;
import java.util.List;

public class UserStaticInfo {
    public static User ActiveUser;
    public final static String POSITION_LATITUDE = "Latitude";
    public final static String POSITION_LONGITUDE = "Longitude";

    // key of login parameter
    public final static String LOGIN = "login";

    public final static String POSITION = "position";
    public  final static String USERS_SIGN_IN_INFO = "UsersSignInInfo";

    public  final static String USERS_PROFILE_INFO = "UsersProfileInfo";

    public  final static String PASSWORD = "password";

    public  final static String PROFILE_ID = "profileId";

    public  final static String NAME = "name";

    public  final static String AGE = "age";

    public  final static String STATE = "state";

    public static String profileId;
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
