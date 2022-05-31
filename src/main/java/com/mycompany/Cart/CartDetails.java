package com.mycompany.Cart;

import javax.persistence.*;

@Entity
@Table(name = "cart_details")
public class CartDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartdetail_id;
    private int cart_id;
    private long product_id;
    private int quantity;


    public int getCartdetail_id() {
        return cartdetail_id;
    }

    public void setCartdetail_id(int cartdetail_id) {
        this.cartdetail_id = cartdetail_id;
    }

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartDetails{" +
                "cartdetail_id=" + cartdetail_id +
                ", cart_id=" + cart_id +
                ", product_id=" + product_id +
                ", quantity=" + quantity +
                '}';
    }
}
