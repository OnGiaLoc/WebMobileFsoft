package com.mycompany.Product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
public class ProductController {
    @Autowired private ProductService service;


    @GetMapping("/product/save")
    public String showNewForm(Model model, HttpSession session, RedirectAttributes ra) {
        int role = 0;
        if (session.getAttribute("role") != null) {
            role = (int) session.getAttribute("role");
        }
        if (role == 1) {
            String x = "admin";
            model.addAttribute("product", new Product());
            model.addAttribute("x", x);
            return "add_product";
        } else {
            ra.addFlashAttribute("message", "You can not access this page!");
            return "redirect:/list_product/show";
        }
    }

    @GetMapping("/list_product/show")
    public String showProduct(Model model, HttpSession httpSession){
        List<Product> listProduct = service.listAll();
        model.addAttribute("listProduct",listProduct);
        return "list_product";
    }

    @GetMapping("/list_product/show/{id}")
    public String showDetail(@PathVariable("id") int id, Model model, HttpSession session, RedirectAttributes ra){
        try {
            Product product = service.get(id);
            model.addAttribute("product", product);
            return "detail_product";
        }catch (ProductNotFoundException e){
            e.printStackTrace();
        }
        return "index";
    }
    @PostMapping("/product/save")
    public String saveProduct(Product product, Model model, @RequestParam("fileImage") MultipartFile multipartFile, RedirectAttributes ra) throws IOException {
        service.save(product);
        String role = "admin";
        model.addAttribute("product", new Product());
        model.addAttribute("role", role);

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        product.setPhoto(fileName);

        Product savedProduct = service.save(product);
        String uploadDir = "./src/main/resources/static/";
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }


        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            System.out.println(filePath.toFile().getAbsolutePath());
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName);
        }
//    model.addAttribute("product", new Product());
        ra.addFlashAttribute("message", "The Product has been saved successfully.");
        return "redirect:/product/save";
    }
}


