package com.modul295_lb1.productmanager.resources.categories;

import com.modul295_lb1.productmanager.resources.products.ProductData;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

/**
 * Controller für die Kategorien (Erstellen, Abrufen, Bearbeiten, Löschen).
 */
@RestController
@RequestMapping("categories")
@Tag(name = "CategoryController", description = "Controller für die Verwaltung (CRUD) der Kategorien")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * Erstellt eine neue Kategorie.
     *
     * @param categoryData Die Kategoriedaten
     * @return Antwort mit dem erstellten Kategorie-Objekt und Status 201 Created
     */
    @PostMapping
    @Operation(summary = "Erstellt eine neue Kategorie",
            operationId = "createCategory",
            description = "Erstellt eine neue Kategorie und gibt das erstellte Kategorie-Objekt zurück.")
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryData categoryData) {
        try {
            if(categoryData.getId() == null) {
                categoryData.setId(1);
            }
            CategoryData createdCategory = categoryService.createCategory(categoryData);
            return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Fehler beim Erstellen der Kategorie: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Ruft die Details einer Kategorie mit einer bestimmten ID ab.
     *
     * @param id Kategorie-ID
     * @return Antwort mit der Kategorie und Status 200 OK oder 404 Not Found
     */
    @GetMapping("/{id}")
    @Operation(summary = "Ruft die Details einer Kategorie ab",
            operationId = "getCategoryById",
            description = "Gibt die Details einer Kategorie mit einer spezifischen ID zurück.")
    public ResponseEntity<?> getCategory(
            @Parameter(description = "ID der Kategorie, die abgerufen werden soll") @PathVariable Integer id) {
        CategoryData category = categoryService.getCategoryById(id);
        return category != null
                ? new ResponseEntity<>(category, HttpStatus.OK)
                : new ResponseEntity<>("Fehler: Kategorie mit ID " + id + " nicht gefunden.", HttpStatus.NOT_FOUND);
    }

    /**
     * Bearbeitet eine Kategorie mit einer bestimmten ID.
     *
     * @param id Die ID der Kategorie
     * @param categoryData Die aktualisierten Kategoriedaten
     * @return Antwort mit der aktualisierten Kategorie und Status 200 OK oder 404 Not Found
     */
    @PutMapping("/{id}")
    @Operation(summary = "Bearbeitet eine bestehende Kategorie",
            operationId = "updateCategory",
            description = "Aktualisiert die Details einer Kategorie mit einer bestimmten ID.")
    public ResponseEntity<?> updateCategory(
            @Parameter(description = "ID der Kategorie, die aktualisiert werden soll") @PathVariable Integer id,
            @Valid @RequestBody CategoryData categoryData) {
        CategoryData updatedCategory = categoryService.updateCategory(id, categoryData);
        return updatedCategory != null
                ? new ResponseEntity<>(updatedCategory, HttpStatus.OK)
                : new ResponseEntity<>("Fehler: Kategorie mit ID " + id + " nicht gefunden.", HttpStatus.NOT_FOUND);
    }

    /**
     * Löscht eine Kategorie mit einer bestimmten ID.
     *
     * @param id Die ID der Kategorie
     * @return Antwort mit Status 204 No Content (+ Bestätigung) oder 404 Not Found (+ Fehlermeldung) und
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Löscht eine Kategorie",
            operationId = "deleteCategory",
            description = "Löscht eine Kategorie mit einer bestimmten ID.")
    public ResponseEntity<?> deleteCategory(
            @Parameter(description = "ID der Kategorie, die gelöscht werden soll") @PathVariable Integer id) {
        if (categoryService.getCategoryById(id) != null) {
            categoryService.deleteCategory(id);
            return new ResponseEntity<>("Kategorie" + id + " erfolgreich gelöscht ", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Fehler: Kategorie mit ID " + id + " nicht gefunden.", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Listet alle verfügbaren Kategorien auf.
     *
     * @return Antwort mit der Liste der Kategorien und Status 200 OK
     */
    @GetMapping("/")
    @Operation(summary = "Listet alle Kategorien auf",
            operationId = "listCategories",
            description = "Gibt eine Liste aller verfügbaren Kategorien zurück.")
    public ResponseEntity<List<CategoryData>> listCategories() {
        List<CategoryData> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    /**
     * Listet alle Produkte einer bestimmten Kategorie auf.
     *
     * @param id Kategorie-ID
     * @return Antwort mit der Liste von Produkten der Kategorie oder 404 Not Found, falls die Kategorie nicht existiert
     */
    @GetMapping("/{id}/products")
    @Operation(summary = "Listet alle Produkte einer Kategorie auf",
            operationId = "listProductsByCategory",
            description = "Gibt eine Liste aller Produkte in einer bestimmten Kategorie zurück.")
    public ResponseEntity<?> listProductsByCategory(
            @Parameter(description = "ID der Kategorie, deren Produkte aufgelistet werden sollen") @PathVariable Integer id) {
        List<ProductData> products = categoryService.getProductsByCategoryId(id);
        return products != null && !products.isEmpty()
                ? new ResponseEntity<>(products, HttpStatus.OK)
                : new ResponseEntity<>("Fehler: Kategorie mit ID " + id + " nicht gefunden oder keine Produkte verfügbar.", HttpStatus.NOT_FOUND);
    }
}
