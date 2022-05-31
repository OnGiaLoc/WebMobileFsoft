package com.mycompany.Product;

import javax.persistence.*;


@Entity
@Table (name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_id;
    @Column(length = 15,nullable = false)
    private String product_name;

    private int price;
    private int stock;
    private String description;
    private String manufacturer;
    private String category;
    private int conditiontype;
    @Column(nullable = true, length = 64)
    private String photo;


    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer id) {
        this.product_id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getConditiontype() {
        return conditiontype;
    }

    public void setConditiontype(int conditiontype) {
        this.conditiontype = conditiontype;
    }

    public String getPhoto() {
        return "/"+ photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Transient
    public String getPhotosImagePath() {
        if (photo == null || "product_id" == null) return null;

        return "./product-photo/" + product_id + "/" + photo;
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + product_id +
                ", product_name='" + product_name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", description='" + description + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", category='" + category + '\'' +
                ", conditiontype=" + conditiontype +
                ", photo='" + photo + '\'' +
                '}';
    }
}
