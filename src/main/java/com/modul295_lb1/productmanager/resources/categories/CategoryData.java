package com.modul295_lb1.productmanager.resources.categories;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.modul295_lb1.productmanager.resources.products.ProductData;
import jakarta.persistence.*;

// Importiert die notwendigen Pakete für JPA und Serialisierung
import java.io.Serializable;
import java.util.List;

/**
 * Entitätsklasse, die eine Kategorie in der Datenbank repräsentiert.
 */
@Entity
@Table(name = "categories")
public class CategoryData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generiert die ID automatisch
    private Integer id;

    @Column(nullable = false) // Die Kategorie muss aktiv/inaktiv sein
    private Boolean active;

    @Column(length = 255, nullable = false)
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference // Verhindert Rekursion beim Serialisieren
    private List<ProductData> products;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<ProductData> getProducts() {
        return products;
    }

    public void setProducts(List<ProductData> products) {
        this.products = products;
    }
}
