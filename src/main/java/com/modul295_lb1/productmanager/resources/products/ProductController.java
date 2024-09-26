package com.modul295_lb1.productmanager.resources.products;

import com.modul295_lb1.productmanager.resources.categories.CategoryData;
import com.modul295_lb1.productmanager.resources.categories.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST-Controller für die Verwaltung von Produkten.
 * Bietet Endpunkte zum Erstellen, Abrufen, Bearbeiten und Löschen von Produkten.
 */
@RestController
@RequestMapping("/products")
@Validated
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService; // Service für Kategorien

    /**
     * Erstellt ein neues Produkt.
     *
     * @param productDTO Die Daten des zu erstellenden Produkts
     * @return Das erstellte Produkt als ResponseEntity
     */
    @Operation(summary = "Erstellt ein neues Produkt",
            description = "Speichert die übergebenen Produktdaten in der Datenbank und verknüpft es mit der entsprechenden Kategorie.")
    @PostMapping
    public ResponseEntity<ProductData> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        ProductData productData = new ProductData();
        productData.setSku(productDTO.getSku());
        productData.setActive(productDTO.getActive());
        productData.setName(productDTO.getName());
        productData.setImage(productDTO.getImage());
        productData.setPrice(productDTO.getPrice());
        productData.setStock(productDTO.getStock());
        productData.setDescription(productDTO.getDescription());

        // Kategorie anhand der ID abrufen
        CategoryData category = categoryService.getCategoryById(productDTO.getCategoryId());
        if (category != null) {
            productData.setCategory(category);
        } else {
            return ResponseEntity.badRequest().body(null); // oder Fehlerbehandlung
        }

        ProductData createdProduct = productService.createProduct(productData);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    /**
     * Ruft die Details eines Produkts mit einer bestimmten ID ab.
     *
     * @param id Die ID des Produkts
     * @return Die Produktdetails als ResponseEntity
     */
    @Operation(summary = "Ruft Produktdetails ab",
            description = "Gibt die Details eines Produkts basierend auf der angegebenen Produkt-ID zurück.")
    @GetMapping("/{id}")
    public ResponseEntity<ProductData> getProduct(@PathVariable Integer id) {
        ProductData productData = productService.getProductById(id);
        return productData != null ? ResponseEntity.ok(productData) : ResponseEntity.notFound().build();
    }

    /**
     * Bearbeitet ein Produkt mit einer bestimmten ID.
     *
     * @param id                Die ID des Produkts, das bearbeitet werden soll
     * @param updatedProductDTO Die aktualisierten Produktdaten
     * @return Das aktualisierte Produkt als ResponseEntity
     */
    @Operation(summary = "Bearbeitet ein bestehendes Produkt",
            description = "Aktualisiert die Daten eines Produkts basierend auf der angegebenen ID.")
    @PutMapping("/{id}")
    public ResponseEntity<ProductData> updateProduct(@PathVariable Integer id, @Valid @RequestBody ProductDTO updatedProductDTO) {
        ProductData updatedProductData = new ProductData();
        updatedProductData.setId(id); // Die ID setzen, um das richtige Produkt zu aktualisieren
        updatedProductData.setSku(updatedProductDTO.getSku());
        updatedProductData.setActive(updatedProductDTO.getActive());
        updatedProductData.setName(updatedProductDTO.getName());
        updatedProductData.setImage(updatedProductDTO.getImage());
        updatedProductData.setPrice(updatedProductDTO.getPrice());
        updatedProductData.setStock(updatedProductDTO.getStock());
        updatedProductData.setDescription(updatedProductDTO.getDescription());

        // Kategorie anhand der ID abrufen
        CategoryData category = categoryService.getCategoryById(updatedProductDTO.getCategoryId());
        if (category != null) {
            updatedProductData.setCategory(category);
        } else {
            return ResponseEntity.badRequest().body(null); // oder Fehlerbehandlung
        }

        ProductData updatedProduct = productService.updateProduct(id, updatedProductData);
        return updatedProduct != null ? ResponseEntity.ok(updatedProduct) : ResponseEntity.notFound().build();
    }

    /**
     * Löscht ein Produkt mit einer bestimmten ID.
     *
     * @param id Die ID des Produkts, das gelöscht werden soll
     * @return Eine Bestätigung, dass das Produkt gelöscht wurde
     */
    @Operation(summary = "Löscht ein Produkt",
            description = "Entfernt das Produkt mit der angegebenen ID aus der Datenbank.")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Produkt " + id + " wurde gelöscht");
    }

    /**
     * Listet alle verfügbaren Produkte auf.
     *
     * @return Eine Liste aller Produkte
     */
    @Operation(summary = "Listet alle Produkte auf",
            description = "Gibt eine Liste aller in der Datenbank gespeicherten Produkte zurück.")
    @GetMapping("/")
    public List<ProductData> listProducts() {
        return productService.getAllProducts();
    }
}
