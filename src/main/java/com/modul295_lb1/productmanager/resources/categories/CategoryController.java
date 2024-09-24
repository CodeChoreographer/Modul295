package com.modul295_lb1.productmanager.resources.categories;

import com.modul295_lb1.productmanager.resources.products.ProductData;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller für Kategorienoperationen (Erstellen, Abrufen, Bearbeiten, Löschen).
 */
@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * Erstellt eine neue Kategorie.
     *
     * @param categoryData Die Kategoriedaten
     * @return Antwort mit dem erstellten Kategorie-Objekt und Status 201 Created
     */
    @PostMapping("/")
    public ResponseEntity<CategoryData> createCategory(@Valid @RequestBody CategoryData categoryData) {
        CategoryData createdCategory = categoryService.createCategory(categoryData);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    /**
     * Ruft die Details einer Kategorie mit einer bestimmten ID ab.
     *
     * @param id Kategorie-ID
     * @return Antwort mit der Kategorie und Status 200 OK oder 404 Not Found
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoryData> getCategory(@PathVariable Integer id) {
        CategoryData category = categoryService.getCategoryById(id);
        return category != null ? new ResponseEntity<>(category, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Bearbeitet eine Kategorie mit einer bestimmten ID.
     *
     * @param id Die ID der Kategorie
     * @param categoryData Die aktualisierten Kategoriedaten
     * @return Antwort mit der aktualisierten Kategorie und Status 200 OK oder 404 Not Found
     */
    @PutMapping("/{id}")
    public ResponseEntity<CategoryData> updateCategory(@PathVariable Integer id, @Valid @RequestBody CategoryData categoryData) {
        CategoryData updatedCategory = categoryService.updateCategory(id, categoryData);
        return updatedCategory != null ? new ResponseEntity<>(updatedCategory, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Löscht eine Kategorie mit einer bestimmten ID.
     *
     * @param id Die ID der Kategorie
     * @return Antwort mit Status 204 No Content oder 404 Not Found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        if (categoryService.getCategoryById(id) != null) {
            categoryService.deleteCategory(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Listet alle verfügbaren Kategorien auf.
     *
     * @return Antwort mit der Liste der Kategorien und Status 200 OK
     */
    @GetMapping("/")
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
    public ResponseEntity<List<ProductData>> listProductsByCategory(@PathVariable Integer id) {
        List<ProductData> products = categoryService.getProductsByCategoryId(id);
        return products != null && !products.isEmpty() ? new ResponseEntity<>(products, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
