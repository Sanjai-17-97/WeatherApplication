package com.example.authenticationservice.repository;

import com.example.authenticationservice.model.UserRegistration;
import com.example.authenticationservice.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WishListRepo extends JpaRepository<WishList,Integer> {

    List<WishList> findByEmail(String email);
    List<WishList> findByCity(String city);
    List<WishList> deleteByEmailAndCity(String email,String city);

    WishList findByEmailAndCity(String email, String city);
}
