package com.modul295_lb1.productmanager.resources.categories;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Dieses Data Transfer Object (DTO) wird verwendet, um Kategoriedaten zwischen
 * Client und Server zu übertragen.
 */
@Data // Generiert Getter, Setter, toString, equals und hashCode
@NoArgsConstructor // Generiert einen Standard-Konstruktor
@AllArgsConstructor // Generiert einen Konstruktor mit allen Feldern
@Schema(description = "DTO für Kategoriedaten, verwendet für die Übertragung von Kategoriedaten zwischen Client und Server.")
public class CategoryDTO {

    @Schema(description = "Die eindeutige ID der Kategorie.", example = "1")
    private Integer id;

    @NotBlank(message = "Name darf nicht leer sein.")
    @Schema(description = "Der Name der Kategorie, der nicht leer sein darf.", example = "Elektronik")
    private String name;

    @Schema(description = "Eine optionale Beschreibung der Kategorie.", example = "Produkte wie Smartphones und Laptops")
    private String description;

    @NotNull(message = "Aktivitätsstatus darf nicht null sein.")
    @Schema(description = "Der Aktivitätsstatus der Kategorie, gibt an, ob die Kategorie aktiv ist.", example = "true")
    private Boolean active;
}
