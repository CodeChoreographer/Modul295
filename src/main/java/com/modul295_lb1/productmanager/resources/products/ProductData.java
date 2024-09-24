package com.modul295_lb1.productmanager.resources.products;

import com.modul295_lb1.productmanager.resources.categories.CategoryData;
import jakarta.persistence.*;
import jdk.jfr.Category;

//import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product")
public class ProductData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String artnr;

    @Column(nullable = false)
    private Boolean active;

    @Column(length = 500)
    private String name;

    @Column(length = 1000)
    private String image;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String description;

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArtnr() {
        return artnr;
    }

    public void setArtnr(String artnr) {
        this.artnr = artnr;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public CategoryData getCategory() {
        return category;
    }

    public void setCategory(CategoryData category) {
        this.category = category;
    }

    @Column(nullable = false)
    private Float price;

    @Column(nullable = false)
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryData category;

    // Getters and setters kommen hier noch
}
