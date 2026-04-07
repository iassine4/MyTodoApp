package fr.fms.mytodoapp.repository;

import fr.fms.mytodoapp.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Gère l'accès aux données des catégories.
 *
 * <p>Cette interface hérite de JpaRepository pour bénéficier
 * automatiquement des opérations CRUD standards.</p>
 *
 * <p>Spring Data JPA sait aussi générer certaines requêtes
 * directement à partir du nom des méthodes.</p>
 *
 * @author Yassine ZRIBA
 * @version 1.0
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * Recherche toutes les catégories dont le nom contient le mot-clé,
     * sans tenir compte de la casse.
     *
     * @param keyword le mot-clé recherché
     * @return la liste des catégories correspondantes
     */
    List<Category> findByNameContainingIgnoreCase(String keyword);

    /**
     * Recherche une catégorie par son nom exact, sans tenir compte de la casse.
     *
     * @param name le nom recherché
     * @return une catégorie optionnelle
     */
    Optional<Category> findByNameIgnoreCase(String name);

    /**
     * Vérifie si une catégorie existe déjà avec ce nom,
     * sans tenir compte de la casse.
     *
     * @param name le nom à tester
     * @return true si la catégorie existe, sinon false
     */
    boolean existsByNameIgnoreCase(String name);
}