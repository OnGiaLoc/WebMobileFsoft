package com.mycompany;

import com.mycompany.Product.Product;
import com.mycompany.Product.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ProductTest {

    @Autowired
    private ProductRepository repo;
    @Test
    public void testAddProduct(){
        Product product = new Product();
        product.setProduct_name("SAMSUNG S22");
        product.setPrice(1000);
        product.setStock(20);
        product.setDescription("Galaxy S22 Ultra 5G chiếc smartphone cao cấp nhất trong bộ 3 Galaxy S22 series mà Samsung đã cho ra mắt.");
        product.setManufacturer("SAMSUNG");
        product.setCategory("samsung");
        product.setConditiontype(1);
        product.setPhoto("https://cdn.tgdd.vn/Products/Images/42/235838/Galaxy-S22-Ultra-Burgundy-600x600.jpg");
        Product savedProduct =repo.save(product);
        Assertions.assertThat(savedProduct).isNotNull();

    }
    @Test
    public void testList(){
        Iterable<Product> products =repo.findAll();
        Assertions.assertThat(products).hasSize(1);
        for(Product product:products){
            System.out.println(product);
        }
    }
}
