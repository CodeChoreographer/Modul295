package com.modul295_lb1.productmanager.resources.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository für Produktoperationen.
 * Bietet CRUD-Operationen für ProductData.
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductData, Integer> {
}
