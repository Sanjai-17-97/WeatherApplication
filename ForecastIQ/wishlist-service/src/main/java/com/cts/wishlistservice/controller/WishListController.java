package com.cts.wishlistservice.controller;

import com.cts.wishlistservice.model.Weather;
import com.cts.wishlistservice.request.WishlistRequest;
import com.cts.wishlistservice.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WishListController {


    @Autowired
    private WishlistService wishlistService;
    // add city in wishlist
    @PostMapping("/wishlist")
    public ResponseEntity<?>addCityToWishList(@RequestBody WishlistRequest wishlist){
        return wishlistService.addCityToWishList(wishlist);
    }

    // get wishlist of a user
    @GetMapping("/wishlist/user/{email}")
    public List<Weather> getWishList(@PathVariable String email) {
        return wishlistService.getWishList(email);
    }
}



