package com.modul295_lb1.productmanager.resources.categories;

import com.modul295_lb1.productmanager.resources.products.ProductData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service-Klasse für Kategorienoperationen.
 * Enthält die Business-Logik für das Erstellen, Abrufen, Bearbeiten und Löschen von Kategorien.
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Erstellt eine neue Kategorie.
     *
     * @param categoryData Die zu erstellenden Kategoriedaten
     * @return Die erstellte Kategorie
     */
    public CategoryData createCategory(CategoryData categoryData) {
        return categoryRepository.save(categoryData);
    }

    /**
     * Ruft die Details einer Kategorie basierend auf der ID ab.
     *
     * @param id Die ID der Kategorie
     * @return Die Kategorie oder null, wenn sie nicht gefunden wurde
     */
    public CategoryData getCategoryById(Integer id) {
        Optional<CategoryData> category = categoryRepository.findById(id);
        return category.orElse(null);
    }

    /**
     * Aktualisiert eine bestehende Kategorie.
     *
     * @param id Die ID der Kategorie
     * @param updatedCategoryData Die aktualisierten Daten der Kategorie
     * @return Die aktualisierte Kategorie
     */
    public CategoryData updateCategory(Integer id, CategoryData updatedCategoryData) {
        Optional<CategoryData> existingCategory = categoryRepository.findById(id);

        if (existingCategory.isPresent()) {
            CategoryData category = existingCategory.get();
            category.setName(updatedCategoryData.getName());
            category.setActive(updatedCategoryData.getActive());
            return categoryRepository.save(category);
        }
        return null; // Alternativ: Exception werfen, wenn Kategorie nicht gefunden
    }

    /**
     * Löscht eine Kategorie basierend auf der ID.
     *
     * @param id Die ID der Kategorie
     */
    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }

    /**
     * Ruft alle Kategorien ab.
     *
     * @return Eine Liste aller Kategorien
     */
    public List<CategoryData> getAllCategories() {
        return categoryRepository.findAll();
    }

    /**
     * Ruft die Produkte einer bestimmten Kategorie ab.
     *
     * @param id Die ID der Kategorie
     * @return Liste von Produkten der Kategorie oder null, wenn Kategorie nicht gefunden wurde
     */
    public List<ProductData> getProductsByCategoryId(Integer id) {
        Optional<CategoryData> category = categoryRepository.findById(id);
        return category.map(CategoryData::getProducts).orElse(null);
    }
}
