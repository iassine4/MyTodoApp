package fr.fms.mytodoapp.service.impl;

import fr.fms.mytodoapp.entity.Category;
import fr.fms.mytodoapp.exception.ResourceNotFoundException;
import fr.fms.mytodoapp.repository.CategoryRepository;
import fr.fms.mytodoapp.service.CategoryService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implémentation des opérations métier liées aux catégories.
 *
 * @author Yassine ZRIBA
 * @version 1.0
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    /**
     * Repository utilisé pour accéder aux données des catégories.
     */
    private final CategoryRepository categoryRepository;

    /**
     * Constructeur avec injection de dépendance.
     *
     * @param categoryRepository le repository des catégories
     */
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Retourne toutes les catégories.
     *
     * @return la liste des catégories
     */
    @Override
    @Transactional(readOnly = true)
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    /**
     * Retourne toutes les catégories triées par nom croissant.
     *
     * @return la liste triée des catégories
     */
    @Override
    @Transactional(readOnly = true)
    public List<Category> getAllCategoriesOrderedByName() {
        return categoryRepository.findAll(Sort.by("name").ascending());
    }

    /**
     * Retourne une catégorie à partir de son identifiant.
     *
     * @param id l'identifiant recherché
     * @return la catégorie trouvée
     */
    @Override
    @Transactional(readOnly = true)
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
    }
}