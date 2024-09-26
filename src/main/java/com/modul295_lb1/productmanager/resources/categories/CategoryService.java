package com.modul295_lb1.productmanager.resources.categories;

import com.modul295_lb1.productmanager.resources.products.ProductData;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service-Klasse für Kategorienoperationen.
 * Enthält die Logik für das Erstellen, Abrufen, Bearbeiten und Löschen von Kategorien.
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Erstellt eine neue Kategorie.
     *
     * @param categoryData Die Kategoriedaten welche erstellt werden
     * @return Die erstellte Kategorie
     * @throws CategoryAlreadyExistsException wenn die Kategorie bereits existiert
     */
    @Operation(summary = "Erstellt eine neue Kategorie",
            description = "Speichert die übergebenen Kategoriedaten in der Datenbank.")
    public CategoryData createCategory(CategoryData categoryData) {
        if (categoryData.getId() != null && categoryRepository.existsById(categoryData.getId())) {
            throw new CategoryAlreadyExistsException("Kategorie mit ID " + categoryData.getId() + " existiert bereits.");
        }
        if (categoryRepository.existsByName(categoryData.getName())) {
            throw new CategoryAlreadyExistsException("Kategorie mit dem Namen '" + categoryData.getName() + "' existiert bereits.");
        }
        System.out.println("Kategorie " + categoryData.getId() + " wurde erfolgreich erstellt.");
        return categoryRepository.save(categoryData);
    }

    /**
     * Ruft die Details einer Kategorie basierend auf der ID ab.
     *
     * @param id Die ID der Kategorie
     * @return Die Kategorie
     * @throws CategoryNotFoundException wenn die Kategorie nicht gefunden wurde
     */
    @Operation(summary = "Ruft eine Kategorie nach ID ab",
            description = "Gibt die Kategorie mit der angegebenen ID zurück.")
    public CategoryData getCategoryById(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new CategoryNotFoundException("Kategorie mit ID " + id + " nicht gefunden."));
    }

    /**
     * Aktualisiert eine bestehende Kategorie.
     *
     * @param id                  Die ID der Kategorie
     * @param updatedCategoryData Die aktualisierten Daten der Kategorie
     * @return Die aktualisierte Kategorie oder null, wenn die Kategorie nicht gefunden wurde
     * @throws CategoryNotFoundException wenn die Kategorie nicht gefunden wurde
     */
    @Operation(summary = "Aktualisiert eine bestehende Kategorie",
            description = "Aktualisiert die Kategorie mit der angegebenen ID basierend auf den neuen Daten.")
    public CategoryData updateCategory(Integer id, CategoryData updatedCategoryData) {
        CategoryData category = categoryRepository.findById(id).orElseThrow(() ->
                new CategoryNotFoundException("Kategorie mit ID " + id + " nicht gefunden."));

        category.setName(updatedCategoryData.getName());
        category.setActive(updatedCategoryData.getActive());
        return categoryRepository.save(category);
    }

    /**
     * Löscht eine Kategorie basierend auf der ID.
     *
     * @param id Die ID der Kategorie
     * @throws CategoryNotFoundException wenn die Kategorie nicht gefunden wurde
     */
    @Operation(summary = "Löscht eine Kategorie",
            description = "Entfernt die Kategorie mit der angegebenen ID aus der Datenbank.")
    public void deleteCategory(Integer id) {
        if (!categoryRepository.existsById(id)) {
            throw new CategoryNotFoundException("Kategorie mit ID " + id + " nicht gefunden.");
        }
        categoryRepository.deleteById(id);
    }

    /**
     * Ruft alle Kategorien ab.
     *
     * @return Eine Liste aller Kategorien
     */
    @Operation(summary = "Ruft alle Kategorien ab",
            description = "Gibt eine Liste aller in der Datenbank gespeicherten Kategorien zurück.")
    public List<CategoryData> getAllCategories() {
        return categoryRepository.findAll();
    }

    /**
     * Ruft die Produkte einer bestimmten Kategorie ab.
     *
     * @param id Die ID der Kategorie
     * @return Liste von Produkten der Kategorie oder null, wenn Kategorie nicht gefunden wurde
     * @throws CategoryNotFoundException wenn die Kategorie nicht gefunden wurde
     */
    @Operation(summary = "Ruft Produkte einer Kategorie ab",
            description = "Gibt die Liste der Produkte zurück, die der Kategorie mit der angegebenen ID zugeordnet sind.")
    public List<ProductData> getProductsByCategoryId(Integer id) {
        CategoryData category = categoryRepository.findById(id).orElseThrow(() ->
                new CategoryNotFoundException("Kategorie mit ID " + id + " nicht gefunden."));
        return category.getProducts();
    }


    /**
     * Benutzerdefinierte Ausnahme, die geworfen wird, wenn eine Kategorie nicht gefunden wird.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    class CategoryNotFoundException extends RuntimeException {
        public CategoryNotFoundException(String message) {
            super(message);
        }
    }

    /**
     * Benutzerdefinierte Ausnahme, die geworfen wird, wenn eine Kategorie bereits existiert.
     */
    @ResponseStatus(HttpStatus.CONFLICT)
    class CategoryAlreadyExistsException extends RuntimeException {
        public CategoryAlreadyExistsException(String message) {
            super(message);
        }
    }
}