package com.modul295_lb1.productmanager.resources.categories;

import org.springframework.web.bind.annotation.*;

/**
 * Controller für Kategorienoperationen (Erstellen, Abrufen, Bearbeiten, Löschen).
 */
@RestController
@RequestMapping("/categories")
public class CategoryController {

    /**
     * Erstellt eine neue Kategorie.
     *
     * @return String, der die erfolgreiche Erstellung anzeigt
     */
    @PostMapping("/")
    public String createCategory() {
        return "Kategorie erstellt";
    }

    /**
     * Ruft die Details einer Kategorie mit einer bestimmten ID ab.
     *
     * @param id Kategorie-ID
     * @return String, der die Kategoriedetails anzeigt
     */
    @GetMapping("/{id}")
    public String getCategory(@PathVariable Long id) {
        return "Kategoriedetails von Kategorie " + id + " abgerufen";
    }

    /**
     * Bearbeitet eine Kategorie mit einer bestimmten ID.
     *
     * @param id Kategorie-ID
     * @return String, der die erfolgreiche Aktualisierung anzeigt
     */
    @PutMapping("/{id}")
    public String updateCategory(@PathVariable Long id) {
        return "Kategorie " + id + " aktualisiert";
    }

    /**
     * Löscht eine Kategorie mit einer bestimmten ID.
     *
     * @param id Kategorie-ID
     * @return String, der die erfolgreiche Löschung anzeigt
     */
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id) {
        return "Kategorie " + id + " gelöscht";
    }

    /**
     * Listet alle verfügbaren Kategorien auf.
     *
     * @return String, der die Kategorieliste anzeigt
     */
    @GetMapping("/")
    public String listCategories() {
        return "Liste aller Kategorien abgerufen";
    }

    /**
     * Listet alle Produkte einer bestimmten Kategorie auf.
     *
     * @param id Kategorie-ID
     * @return String, der die Produktliste anzeigt
     */
    @GetMapping("/{id}/products")
    public String listProductsByCategory(@PathVariable Long id) {
        return "Liste der Produkte für Kategorie " + id + " abgerufen";
    }
}
