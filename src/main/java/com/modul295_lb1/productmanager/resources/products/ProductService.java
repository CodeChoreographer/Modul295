package com.modul295_lb1.productmanager.resources.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service für Produktoperationen.
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
     * @param productData Produktdaten
     * @return das erstellte Produkt
     * @throws IllegalArgumentException wenn die Produktdaten ungültig sind
     */
    public ProductData createProduct(ProductData productData) {
        validateProductData(productData); // Validierung der Produktdaten
        return productRepository.save(productData);
    }

    /**
     * Ruft die Details eines Produkts anhand der ID ab.
     *
     * @param id Produkt-ID
     * @return das Produkt, falls gefunden
     */
    public ProductData getProductById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    /**
     * Aktualisiert ein bestehendes Produkt.
     *
     * @param id          Produkt-ID
     * @param productData aktualisierte Produktdaten
     * @return das aktualisierte Produkt, falls gefunden
     * @throws IllegalArgumentException wenn die Produktdaten ungültig sind
     */
    public ProductData updateProduct(Integer id, ProductData productData) {
        if (productRepository.existsById(id)) {
            validateProductData(productData); // Validierung der Produktdaten
            productData.setId(id);
            return productRepository.save(productData);
        }
        return null; // Produkt nicht gefunden
    }

    /**
     * Löscht ein Produkt anhand der ID.
     *
     * @param id Produkt-ID
     */
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    /**
     * Listet alle verfügbaren Produkte auf.
     *
     * @return Liste der Produkte
     */
    public List<ProductData> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Validiert die Produktdaten.
     *
     * @param productData die zu validierenden Produktdaten
     * @throws IllegalArgumentException wenn ein erforderliches Feld fehlt oder ungültig ist
     */
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
            throw new IllegalArgumentException("Preis muss größer als 0 sein.");
        }
        if (productData.getStock() == null || productData.getStock() < 0) {
            throw new IllegalArgumentException("Bestand kann nicht negativ sein.");
        }
        if (productData.getCategory() == null) {
            throw new IllegalArgumentException("Kategorie ist erforderlich.");
        }
    }
}
