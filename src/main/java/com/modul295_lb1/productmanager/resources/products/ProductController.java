package com.modul295_lb1.productmanager.resources.products;

import org.springframework.web.bind.annotation.*;

/**
 * Controller für Produktoperationen (Erstellen, Abrufen, Bearbeiten, Löschen).
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    /**
     * Erstellt ein neues Produkt.
     *
     * @return String, der die erfolgreiche Erstellung anzeigt
     */
    @PostMapping("/")
    public String createProduct() {
        return "Produkt erstellt";
    }

    /**
     * Ruft die Details eines Produkts mit einer bestimmten ID ab.
     *
     * @param id Produkt-ID
     * @return String, der die Produktdetails anzeigt
     */
    @GetMapping("/{id}")
    public String getProduct(@PathVariable Long id) {
        return "Produktdetails von Produkt " + id + " abgerufen";
    }

    /**
     * Bearbeitet ein Produkt mit einer bestimmten ID.
     *
     * @param id Produkt-ID
     * @return String, der die erfolgreiche Aktualisierung anzeigt
     */
    @PutMapping("/{id}")
    public String updateProduct(@PathVariable Long id) {
        return "Produkt " + id + " aktualisiert";
    }

    /**
     * Löscht ein Produkt mit einer bestimmten ID.
     *
     * @param id Produkt-ID
     * @return String, der die erfolgreiche Löschung anzeigt
     */
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        return "Produkt " + id + " gelöscht";
    }

    /**
     * Listet alle verfügbaren Produkte auf.
     *
     * @return String, der die Produktliste anzeigt
     */
    @GetMapping("/")
    public String listProducts() {
        return "Liste aller Produkte abgerufen";
    }
}
