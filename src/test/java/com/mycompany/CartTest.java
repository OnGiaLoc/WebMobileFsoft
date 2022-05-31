package com.mycompany;

import com.mycompany.Cart.Cart;
import com.mycompany.Cart.CartRepository;
import com.mycompany.Login.Login;
import com.mycompany.Login.LoginRepository;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)

public class CartTest {

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private LoginRepository loginRepo;
    @Test
    public void TestaddCart(){
        Cart cart = new Cart();
        List<Login> listAll = (List<Login>) loginRepo.findAll();
        System.out.println(listAll.get(1).getUsername());
        cart.setUsername(listAll.get(1).getUsername());
        Cart savedLogin = cartRepo.save(cart);
        Assertions.assertThat(savedLogin).isNotNull();
    }
}
