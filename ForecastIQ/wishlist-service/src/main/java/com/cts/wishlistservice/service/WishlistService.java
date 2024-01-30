package com.cts.wishlistservice.service;

import com.cts.wishlistservice.model.Weather;
import com.cts.wishlistservice.request.WishlistRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface WishlistService {


    ResponseEntity<?> addCityToWishList(WishlistRequest wishlist);

    List<Weather> getWishList(String email);
}
