package com.mycompany.Login;

import com.mycompany.Cart.CartService;
import com.mycompany.Login.LoginService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
public class LoginController {

    @Autowired
    private LoginService userService;

//    @GetMapping("/")
//    public String showLoginForm(Model model){
//        model.addAttribute("user", new Login());
//    return "login";
//    }

    @Autowired
    private CartService cartService;

    @GetMapping("")
    public String showLoginForm(Model model, HttpSession session, RedirectAttributes ra) {
        if (session.getAttribute("username") == null) {
            model.addAttribute("user", new Login());
            return "index";
        } else {
            int role = 0;
            if (session.getAttribute("role") != null) {
                role = (int) session.getAttribute("role");
            }
            if (role == 1) {
                String x = "admin";
                model.addAttribute("x", x);
                return "redirect:/product/save";
            } else {
                return "redirect:/list_product/show";
            }
        }
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") Login user, RedirectAttributes ra, HttpSession session, Model model) {
        if (userService.login(user.getUsername(), user.getPassword())) {
            int cartid = cartService.GetCart_id(user.getUsername());
            int role = userService.gettypeuser(user.getUsername());
            String user1 = user.getUsername().toLowerCase().trim();
            session.setAttribute("username", user);
            session.setAttribute("cartid", cartid);
            session.setAttribute("role", role);
            if (role == 1) {
                String x = "admin";
                model.addAttribute("x", x);
                return "redirect:/product/save";
            } else {
                return "redirect:/list_product/show";
            }
        } else {
            return "redirect:/";
        }
    }

//        Login oauthUser = userService.login(user.getUsername(), user.getPassword());
//
//
//        System.out.print(oauthUser);
//        if (Objects.nonNull(oauthUser)) {
//
//            return "redirect:/add_product";
//
//
//        } else {
//            return "redirect:/login";
//
//
//        }
//
//    }

    @RequestMapping(value = {"/logout"})
    public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        session.removeAttribute("username");
        session.removeAttribute("cardit");
        session.removeAttribute("role");

        return "redirect:/";
    }

}