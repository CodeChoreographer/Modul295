package com.modul295_lb1.productmanager.resources.products;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.converter.json.GsonBuilderUtils;

/**
 * Data Transfer Object für Produktdaten.
 */
public class ProductDTO {

    @NotNull(message = "ArtNr darf nicht null sein.")
    private String sku;

    @NotNull(message = "Aktivitätsstatus darf nicht null sein.")
    private Boolean active;

    @NotBlank(message = "Name darf nicht leer sein.")
    private String name;

    private String image;

    @NotNull(message = "Preis darf nicht null sein.")
    @Positive(message = "Preis muss positiv sein.")
    private Float price;

    @NotNull(message = "Lagerbestand darf nicht null sein.")
    @PositiveOrZero(message = "Lagerbestand muss null oder positiv sein.")
    private Integer stock;

    private String description;

    @NotNull(message = "Kategorie-ID darf nicht null sein.")
    private Integer categoryId;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCategoryId() { // Getter für die Kategorie-ID
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) { // Setter für die Kategorie-ID
        this.categoryId = categoryId;
    }
}
