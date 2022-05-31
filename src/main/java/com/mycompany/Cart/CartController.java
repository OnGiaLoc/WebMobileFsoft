package com.mycompany.Cart;


import com.mycompany.Product.Product;
import com.mycompany.Product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CartDetailsService cartDetailsService;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/list_product/addCart/{id}")
    public String addCart(@PathVariable("id") int id, HttpSession session, RedirectAttributes ra) {
        int cartid = (int) session.getAttribute("cartid");
        if (cartDetailsService.addCartDetails(id, cartid)) {
            ra.addFlashAttribute("message", "Your order has been successfully placed");
        }
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String showCart(HttpSession session, Model model) {
        int role = 0;
        if (session.getAttribute("role") != null) {
            role = (int) session.getAttribute("role");
        }
        if (role == 1) {
            String x = "admin";
            model.addAttribute("x", x);
            return "redirect:/";
        } else if (role == 0) {
            return "redirect:/";
        } else {
            int cartid = (int) session.getAttribute("cartid");
            List<CartDetails> getCartDetails = cartDetailsService.getCartDetail(cartid);
            model.addAttribute("getCartDetails", getCartDetails);
            int total = 0;

            List<Product> getProduct = new ArrayList<>();
            for (int i = 0; i < getCartDetails.size(); i++) {
                Product product = productRepository.findById((int) getCartDetails.get(i).getProduct_id()).get();
                getProduct.add(product);
               int x = product.getPrice()*getCartDetails.get(i).getQuantity();
                total += x;
            }
            model.addAttribute("getProduct", getProduct);
            model.addAttribute("total", total);
            return "cart";
        }
    }

    @GetMapping("/cart/remove/{id}")
    public String removeCart(@PathVariable("id") int id, RedirectAttributes ra, HttpSession session, Model model) {
        int role = 0;
        if (session.getAttribute("role") != null) {
            role = (int) session.getAttribute("role");
        }
        if (role == 1) {
            String x = "admin";
            model.addAttribute("x", x);
            return "redirect:/";
        } else if (role == 0) {
            return "redirect:/";
        } else {
            cartDetailsService.removeCartProduct(id);
            ra.addFlashAttribute("message", "Remove Successfully.");
            return "redirect:/cart";
        }
    }

    @GetMapping("/cart/clear")
    public String clearCart(HttpSession session, RedirectAttributes ra, Model model) {
        int role = 0;
        if (session.getAttribute("role") != null) {
            role = (int) session.getAttribute("role");
        }
        if (role == 1) {
            String x = "admin";
            model.addAttribute("x", x);
            return "redirect:/";
        } else if (role == 0) {
            return "redirect:/";
        } else {
            int cartid = (int) session.getAttribute("cartid");
            cartDetailsService.clearCart(cartid);
            ra.addFlashAttribute("message", "Claer cart successfully.");
            return "redirect:/cart";
        }
    }

    @GetMapping("list_product/show/product/addCart/{id}")
    public String addCart2(@PathVariable("id") int id, HttpSession session, Model model) {
        int role = 0;
        if (session.getAttribute("role") != null) {
            role = (int) session.getAttribute("role");
        }
        if (role == 1) {
            String x = "admin";
            model.addAttribute("x", x);
            return "redirect:/";
        } else if (role == 0) {
            return "redirect:/";
        } else {
            return "redirect:/list_product/addCart/" + id;
        }
    }
}