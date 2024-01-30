package com.example.authenticationservice.serviceImpl;


import com.example.authenticationservice.model.Login;
import com.example.authenticationservice.model.UserRegistration;
import com.example.authenticationservice.model.WishList;
import com.example.authenticationservice.repository.UserRegistrationRepo;
import com.example.authenticationservice.repository.WishListRepo;
import com.example.authenticationservice.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    UserRegistrationRepo userRegistrationRepo;
    @Autowired
    WishListRepo wishListRepo;
//    @Autowired
//    WishList wishList;
    /**
     *
     * @param user
     * @return
     */
    @Override
    public UserRegistration registerUser(UserRegistration user) {
        return userRegistrationRepo.save(user);
    }

    @Override
    public WishList registerTemp(WishList wish, String email) {
        WishList wishList = new WishList();
        wishList.setCity(wish.getCity());
        wishList.setEmail(email);
        wishList.setWeather(wish.getWeather());
        wishList.setTemperature(wish.getTemperature());
        return wishListRepo.save(wishList);
    }

    /**
     *
     * @param login
     * @return
     */
    @Override
    public boolean validateLogin(Login login) {
        Optional<UserRegistration> isUserPresent = getUserByMail(login.getEmail());
        boolean isValid = isUserPresent.isPresent();
        log.info(String.valueOf(isValid));
        if (isValid) {

            log.info(String.valueOf("optional " + isUserPresent.get().getEmail() == login.getEmail()));
            if (login.getPassword().equals(isUserPresent.get().getPassword())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<WishList> getWeatherDetails(String email) {
        return wishListRepo.findByEmail(email);
    }

    /**
     *  Delete wish List
     * @param email
     * @param city
     * @return
     */
    @Override
    public Boolean deleteWishlist(String email, String city) {
//        List<WishList> emailWishlist = wishListRepo.findByEmail(email);
//        List<WishList> cityWishlist = wishListRepo.findByCity(city);
//        System.out.println("controle");
//        if(!emailWishlist.isEmpty() && !cityWishlist.isEmpty()){
//            wishListRepo.deleteByEmailAndCity(email,city);
//            return true;
//        }

        WishList toDeleteWishList = wishListRepo.findByEmailAndCity(email,city);
        if (toDeleteWishList != null) {
            wishListRepo.deleteByEmailAndCity(email,city);
            return true;
        }
        return false;
    }

//    @Override
//    public boolean validateUser(WishList wish, Login login) {
//        Optional<UserRegistration> isUserPresent = getUserByMail(wish.getEmail());
//        boolean isValid = isUserPresent.isPresent();
//        log.info(String.valueOf(isValid));
//        if (isValid) {
//
//            log.info(String.valueOf("optional " + isUserPresent.get().getEmail() == wish.getEmail()));
//            if (wish.getEmail().equals(isUserPresent.get().getEmail())) {
//                return true;
//            }
//        }
//        return false;
//    }


    public Optional<UserRegistration> getUserByMail(String mail) {
        return userRegistrationRepo.findByEmail(mail);
    }


}
