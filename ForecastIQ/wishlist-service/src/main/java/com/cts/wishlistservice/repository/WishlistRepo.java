package com.cts.wishlistservice.repository;


import com.cts.wishlistservice.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WishlistRepo extends JpaRepository<Wishlist, Integer> {

    Wishlist findByEmail(String email);
}
