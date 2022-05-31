package com.mycompany.Cart;

import com.mycompany.Product.Product;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends  CrudRepository<Cart, Integer> {


}
