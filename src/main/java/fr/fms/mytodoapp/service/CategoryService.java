package fr.fms.mytodoapp.service;

import fr.fms.mytodoapp.entity.Category;

import java.util.List;

/**
 * Définit les opérations métier liées à la gestion des catégories.
 *
 * <p>Cette interface appartient à la couche service.</p>
 *
 * @author Yassine ZRIBA
 * @version 1.0
 */
public interface CategoryService {

    /**
     * Retourne toutes les catégories.
     *
     * @return la liste des catégories
     */
    List<Category> getAllCategories();

    /**
     * Retourne toutes les catégories triées par nom croissant.
     *
     * @return la liste triée des catégories
     */
    List<Category> getAllCategoriesOrderedByName();

    /**
     * Retourne une catégorie à partir de son identifiant.
     *
     * @param id l'identifiant recherché
     * @return la catégorie trouvée
     */
    Category getCategoryById(Long id);
}