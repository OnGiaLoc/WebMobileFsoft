package com.mycompany.Cart;

import com.mycompany.Product.Product;
import com.mycompany.Product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartDetailsService {
    @Autowired
    private CartDetailsRepository cartdetailsRepo;

    @Autowired
    private ProductRepository productRepo;

    public boolean addCartDetails(int product_id, int cartid) {
        List<CartDetails> listCartDetails = (List<CartDetails>) cartdetailsRepo.findAll();
        List<CartDetails> userCartDetails = new ArrayList<>();
        Product product = productRepo.findById(product_id).get();
        boolean checkAvailable = false;

        for (int i = 0; i < listCartDetails.size(); i++) {
            if (listCartDetails.get(i).getCart_id() == cartid) {
                userCartDetails.add(listCartDetails.get(i));
            }
        }
        if (userCartDetails.size() == 0 && product.getStock() != 0) {
            CartDetails cartDetails = new CartDetails();
            cartDetails.setProduct_id(product_id);
            cartDetails.setCart_id(cartid);
            cartDetails.setQuantity(1);
            product.setStock(product.getStock() - 1);
            productRepo.save(product);
            cartdetailsRepo.save(cartDetails);
        } else {
            for (int i = 0; i < userCartDetails.size(); i++) {
                if (userCartDetails.get(i).getProduct_id() == product_id && product.getStock() != 0){
                    userCartDetails.get(i).setQuantity(userCartDetails.get(i).getQuantity()+1);
                    cartdetailsRepo.save(userCartDetails.get(i));
                    product.setStock(product.getStock()-1);
                    productRepo.save(product);
                    checkAvailable = true;
                    break;
                }
            }

            if (checkAvailable == false && product.getStock() != 0) {
                CartDetails cartDetails = new CartDetails();
                cartDetails.setProduct_id(product_id);
                cartDetails.setCart_id(cartid);
                cartDetails.setQuantity(1);
                cartdetailsRepo.save(cartDetails);
                product.setStock(product.getStock()-1);
                productRepo.save(product);
                checkAvailable = true;
            }
        }
        return checkAvailable;
    }

    public List<CartDetails> getCartDetail(int cartid) {
        List<CartDetails> cartDetails = new ArrayList<>();
        List<CartDetails> getAllCartDetails = (List<CartDetails>) cartdetailsRepo.findAll();
        for (int i = 0; i < getAllCartDetails.size(); i++) {
            if (getAllCartDetails.get(i).getCart_id() == cartid) {
                cartDetails.add(getAllCartDetails.get(i));
            }
        }
        return cartDetails;
    }

    public void removeCartProduct(int cardetailsid) {
        cartdetailsRepo.deleteById(cardetailsid);
    }

    public void clearCart(int cartid) {
        List<CartDetails> listCartDetails = (List<CartDetails>) cartdetailsRepo.findAll();
        for (int i = 0; i < listCartDetails.size(); i++) {
            if (listCartDetails.get(i).getCart_id() == cartid) {
                cartdetailsRepo.deleteById(listCartDetails.get(i).getCartdetail_id());
            }
        }
    }
}



