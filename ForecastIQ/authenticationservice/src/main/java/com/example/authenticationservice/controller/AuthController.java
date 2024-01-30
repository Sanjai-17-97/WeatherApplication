package com.example.authenticationservice.controller;

import com.example.authenticationservice.model.Login;
import com.example.authenticationservice.model.UserRegistration;
import com.example.authenticationservice.model.WishList;
import com.example.authenticationservice.service.AuthService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
//@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, allowedHeaders = "*", allowCredentials = "true")
@CrossOrigin("*")
public class AuthController {
    Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;


    // ----- dummy function -----------
    @GetMapping("/welcome")
    public String welcome(){
        return "from auth controller";
    }

    // Register new user
    @PostMapping("/reg")
    public ResponseEntity<String> saveUser(@RequestBody UserRegistration userRegistration){
        authService.registerUser(userRegistration);
        return new ResponseEntity<>("Registration Completed Successfully", HttpStatus.OK);
    }
    //API call
    @GetMapping("/get/{email}")
    public List<WishList> getWeatherDetails(@PathVariable String email){

        return authService.getWeatherDetails(email);
    }
    //Saving Wishlist
    @PostMapping("/wish/{email}")
    public ResponseEntity<?> saveWish(@RequestBody WishList wishList, @PathVariable String email){
        log.info("--------login email-----"+wishList.getEmail());
        boolean result = true;

        if(result){
//            String token = generateToken(email);
//            HashMap hashMap = new HashMap();
//            hashMap.put("token",token);
            authService.registerTemp(wishList,email);
            return new ResponseEntity<>("Added Successfully",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<String>("Invalid Credentails",HttpStatus.UNAUTHORIZED);
        }
    }
    //delete wishlist by mail and city
    @DeleteMapping("/delete/{email}/{city}")
    public ResponseEntity<?> deleteWish(@PathVariable String email,@PathVariable String city){
        log.info("---------in delete method" + email+city);
        Boolean del= authService.deleteWishlist(email,city);
        if(del) {
            return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("City not in Wishlist",HttpStatus.NOT_FOUND);
    }
   // Login as existing user
    @PostMapping("login")
    public ResponseEntity<?> loginUser(@RequestBody Login login){
        boolean result = authService.validateLogin(login);

        if(result){
            String token = generateToken(login);
            HashMap hashMap = new HashMap();
            hashMap.put("token",token);
            String email = login.getEmail();
            hashMap.put("mail",email);
            return  new ResponseEntity<HashMap>(hashMap,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<String>("Invalid Credentails",HttpStatus.UNAUTHORIZED);
        }
    }

    private String generateToken(Login login){
        return Jwts.builder().setSubject(login.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 300000))
                .signWith(SignatureAlgorithm.HS256,"cgidemo").compact();
    }

}