package com.cts.wishlistservice.request;

import jakarta.persistence.UniqueConstraint;

import java.util.List;

public class WishlistRequest {

    String email;
    List<String> city;

    public WishlistRequest() {
    }

    public WishlistRequest(String email, List<String> city) {
        this.email = email;
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getCity() {
        return city;
    }

    public void setCity(List<String> city) {
        this.city = city;
    }
}
