package com.example.authenticationservice.service;


import com.example.authenticationservice.model.Login;
import com.example.authenticationservice.model.UserRegistration;
import com.example.authenticationservice.model.WishList;

import java.util.List;

public interface AuthService {
    UserRegistration registerUser(UserRegistration user);
    WishList registerTemp(WishList wish, String email);

    boolean validateLogin(Login login);
    List<WishList> getWeatherDetails(String email);

    Boolean deleteWishlist(String email, String city);
//    boolean validateUser(WishList wish,Login login);
}
