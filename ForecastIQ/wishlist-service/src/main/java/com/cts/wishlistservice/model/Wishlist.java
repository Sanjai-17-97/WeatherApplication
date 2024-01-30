package com.cts.wishlistservice.model;
import jakarta.persistence.*;

import java.util.List;
@Entity
@Table
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    String email;
    List<String> city;
    public Wishlist() {
    }
    public Wishlist(Integer id, String email, List<String> city) {
        this.id = id;
        this.email = email;
        this.city = city;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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