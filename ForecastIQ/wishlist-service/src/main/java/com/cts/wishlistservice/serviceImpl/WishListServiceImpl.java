package com.cts.wishlistservice.serviceImpl;

import com.cts.wishlistservice.model.Weather;
import com.cts.wishlistservice.model.Wishlist;
import com.cts.wishlistservice.repository.WishlistRepo;
import com.cts.wishlistservice.request.WishlistRequest;
import com.cts.wishlistservice.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishListServiceImpl implements WishlistService {

    @Autowired
    private WishlistRepo wishlistRepo;

    //add your api key here
    private static final String API_KEY = "d00d195be0f1f00c2913bed066ddd18e";

    //add the base api url here
    private static final String API_URL = "http://api.weatherstack.com/current";

    RestTemplate restTemplate = new RestTemplate();

    /**
     *  add City To WishList of a user
     * @param wishlist
     * @return
     */
    @Override
    public ResponseEntity<?> addCityToWishList(WishlistRequest wishlist) {
        Wishlist userWishList = new Wishlist();
        userWishList.setEmail(wishlist.getEmail());
        userWishList.setCity(wishlist.getCity());

        return new ResponseEntity<>(wishlistRepo.save(userWishList), HttpStatus.OK);
    }

    /**
     *
     * @param email
     * @return
     */
    @Override
    public List<Weather> getWishList(String email) {
        Wishlist userWishList = new Wishlist();
        userWishList = wishlistRepo.findByEmail(email);

       List<String> wishlistCity =  userWishList.getCity();
       List<Weather> weather = new ArrayList<>();
        for (String city: wishlistCity) {
            System.out.println("city ----- " + city);
            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(API_URL)
                    .queryParam("access_key", API_KEY)
                    .queryParam("query", city);
            String urlWithParams = builder.toUriString();
            ResponseEntity<Weather> responseEntity = restTemplate.exchange(urlWithParams, HttpMethod.GET, null, Weather.class);

            weather.add(responseEntity.getBody());
        }

        return weather;
    }


}
