package com.modul295_lb1.productmanager.resources.products;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object für Produktdaten.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
