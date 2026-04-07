package fr.fms.mytodoapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Représente une catégorie de tâches.
 *
 * <p>Une catégorie permet de regrouper plusieurs tâches
 * selon un thème, par exemple : travail, maison, courses.</p>
 *
 * <p>Cette première version reste volontairement simple :
 * elle contient seulement un identifiant et un nom.</p>
 *
 * @author -
 * @version 1.0
 */
@Entity
@Table(name = "categories")
public class Category {

    /**
     * Identifiant unique de la catégorie.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nom de la catégorie.
     */
    @NotBlank(message = "Le nom de la catégorie est obligatoire.")
    @Size(max = 100, message = "Le nom de la catégorie ne doit pas dépasser 100 caractères.")
    private String name;

    /**
     * Constructeur vide obligatoire pour JPA.
     */
    public Category() {
    }

    /**
     * Constructeur pratique pour créer une catégorie avec son nom.
     *
     * @param name le nom de la catégorie
     */
    public Category(String name) {
        this.name = name;
    }

    /**
     * Retourne l'identifiant de la catégorie.
     *
     * @return l'identifiant
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit l'identifiant de la catégorie.
     *
     * @param id le nouvel identifiant
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retourne le nom de la catégorie.
     *
     * @return le nom de la catégorie
     */
    public String getName() {
        return name;
    }

    /**
     * Définit le nom de la catégorie.
     *
     * @param name le nouveau nom de la catégorie
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retourne une représentation textuelle de la catégorie.
     *
     * @return une chaîne lisible pour le débogage
     */
    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}