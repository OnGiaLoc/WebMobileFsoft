package com.mycompany.Cart;


import com.mycompany.Product.Product;
import com.mycompany.Login.Login;
import com.mycompany.Product.ProductRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepo;

    public int GetCart_id(String username) {
        System.out.println("Hello");
        List<Cart> listCart = (List<Cart>) cartRepo.findAll();
        boolean checkAvailable = false;
        int cartId = 0;
        for (int i = 0; i < listCart.size(); i++) {
            if (listCart.get(i).getUsername().toLowerCase().trim().equals(username.toLowerCase().trim())) {
                checkAvailable = true;
                cartId = listCart.get(i).getCart_id();
                break;
            }
        }
        if (checkAvailable == false) {
            Cart cart = new Cart();
            cart.setUsername(username);
            cartRepo.save(cart);
            cartId = cart.getCart_id();
        }
        return cartId;
    }
}