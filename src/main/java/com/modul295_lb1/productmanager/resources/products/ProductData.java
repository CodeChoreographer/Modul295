package com.modul295_lb1.productmanager.resources.products;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.modul295_lb1.productmanager.resources.categories.CategoryData;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.io.Serializable;

/**
 * Entity-Klasse, die die Produktdatenbankstruktur abbildet.
 */
@Entity
@Table(name = "products")
public class ProductData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "ArtNr darf nicht null sein.")
    @Column(length = 100, nullable = false)
    private String sku;

    @NotNull(message = "Aktivitätsstatus darf nicht null sein.")
    @Column(nullable = false)
    private Boolean active;

    @NotNull(message = "Name darf nicht null sein.")
    @Column(length = 500, nullable = false)
    private String name;

    @Column(length = 1000)
    private String image;

    @NotNull(message = "Preis darf nicht null sein.")
    @Positive(message = "Preis muss positiv sein.")
    @Column(nullable = false)
    private Float price;

    @NotNull(message = "Lagerbestand darf nicht null sein.")
    @PositiveOrZero(message = "Lagerbestand muss null oder positiv sein.")
    @Column(nullable = false)
    private Integer stock;

    /**
     * Referenz zur Kategorie, in der das Produkt eingeordnet ist.
     * Verwenden von JsonBackReference, um Unbegrenztes erstellen zu verhindern.
     */
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @JsonBackReference
    private CategoryData category;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
