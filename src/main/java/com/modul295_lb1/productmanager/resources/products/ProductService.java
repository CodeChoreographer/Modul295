package com.modul295_lb1.productmanager.resources.products;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service-Klasse für Produktoperationen.
 * Enthält die Logik für das Erstellen, Abrufen, Bearbeiten und Löschen von Produkten.
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Erstellt ein neues Produkt.
     *
     * @param productData Die Produktdaten, die erstellt werden sollen
     * @return Das erstellte Produkt
     * @throws IllegalArgumentException wenn die Produktdaten ungültig sind
     */
    @Operation(summary = "Erstellt ein neues Produkt",
            description = "Speichert die übergebenen Produktdaten in der Datenbank.")
    public ProductData createProduct(ProductData productData) {
        validateProductData(productData); // Validierung der Produktdaten
        return productRepository.save(productData);
    }

    /**
     * Ruft die Details eines Produkts basierend auf der ID ab.
     *
     * @param id Die ID des Produkts
     * @return Das Produkt, falls gefunden
     */
    @Operation(summary = "Ruft ein Produkt nach ID ab",
            description = "Gibt die Produktdetails anhand der angegebenen ID zurück.")
    public ProductData getProductById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    /**
     * Aktualisiert ein bestehendes Produkt.
     *
     * @param id          Die ID des Produkts
     * @param productData Die aktualisierten Produktdaten
     * @return Das aktualisierte Produkt, falls vorhanden
     * @throws IllegalArgumentException wenn die Produktdaten ungültig sind
     */
    @Operation(summary = "Aktualisiert ein bestehendes Produkt",
            description = "Aktualisiert die Produktinformationen basierend auf der ID und den neuen Daten.")
    public ProductData updateProduct(Integer id, ProductData productData) {
        if (productRepository.existsById(id)) {
            validateProductData(productData); // Validierung der Produktdaten
            productData.setId(id);
            return productRepository.save(productData);
        }
        return null; // Produkt nicht gefunden
    }

    /**
     * Löscht ein Produkt basierend auf der ID.
     *
     * @param id Die ID des Produkts
     */
    @Operation(summary = "Löscht ein Produkt",
            description = "Entfernt das Produkt mit der angegebenen ID aus der Datenbank.")
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    /**
     * Ruft alle verfügbaren Produkte ab.
     *
     * @return Eine Liste aller Produkte
     */
    @Operation(summary = "Ruft alle verfügbaren Produkte ab",
            description = "Gibt eine Liste aller in der Datenbank gespeicherten Produkte zurück.")
    public List<ProductData> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Validiert die Produktdaten.
     *
     * @param productData Die zu validierenden Produktdaten
     * @throws IllegalArgumentException wenn ein erforderliches Feld fehlt oder ungültig ist
     */
    @Operation(summary = "Validiert die Produktdaten",
            description = "Überprüft, ob die erforderlichen Felder des Produkts gültig sind.")
    private void validateProductData(ProductData productData) {
        if (productData.getSku() == null || productData.getSku().isEmpty()) {
            throw new IllegalArgumentException("Artikelnummer ist erforderlich.");
        }
        if (productData.getActive() == null) {
            throw new IllegalArgumentException("Aktivstatus ist erforderlich.");
        }
        if (productData.getName() == null || productData.getName().isEmpty()) {
            throw new IllegalArgumentException("Produktname ist erforderlich.");
        }
        if (productData.getPrice() == null || productData.getPrice() <= 0) {
            throw new IllegalArgumentException("Preis muss grösser als 0 sein.");
        }
        if (productData.getStock() == null || productData.getStock() < 0) {
            throw new IllegalArgumentException("Bestand kann nicht negativ sein.");
        }
        if (productData.getCategory() == null) {
            throw new IllegalArgumentException("Kategorie ist erforderlich.");
        }
    }
}
