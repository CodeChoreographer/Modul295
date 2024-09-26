package com.modul295_lb1.productmanager.resources.categories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository für die Kategorie-Entität.
 * Erweitert JpaRepository für CRUD-Operationen.
 */
@Repository
public interface CategoryRepository extends JpaRepository<CategoryData, Integer> {

    /**
     * Findet eine Kategorie basierend auf dem Namen.
     *
     * @param name Der Name der Kategorie
     * @return Eine Optionale Kategorie
     */
    Optional<CategoryData> findByName(String name);

    /**
     * Überprüft, ob eine Kategorie mit dem angegebenen Namen existiert.
     *
     * @param name Der Name der Kategorie
     * @return true, wenn die Kategorie existiert, andernfalls false
     */
    boolean existsByName(String name);
}
