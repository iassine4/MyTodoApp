package fr.fms.mytodoapp.repository;

import fr.fms.mytodoapp.entity.Category;
import fr.fms.mytodoapp.entity.Task;
import fr.fms.mytodoapp.enums.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Gère l'accès aux données des tâches.
 *
 * <p>Cette interface hérite de JpaRepository pour bénéficier
 * automatiquement des opérations CRUD standards.</p>
 *
 * <p>Elle contient aussi quelques méthodes de recherche utiles
 * pour le projet TodoList.</p>
 *
 * @author Yassine ZRIBA
 * @version 1.0
 */
public interface TaskRepository extends JpaRepository<Task, Long> {

    /**
     * Recherche les tâches dont le titre contient le mot-clé,
     * sans tenir compte de la casse, avec pagination.
     *
     * @param keyword le mot-clé recherché
     * @param pageable les informations de pagination
     * @return une page de tâches correspondantes
     */
    Page<Task> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);

    /**
     * Recherche les tâches dont le titre ou la description contient le mot-clé,
     * sans tenir compte de la casse, avec pagination.
     *
     * @param titleKeyword le mot-clé recherché dans le titre
     * @param descriptionKeyword le mot-clé recherché dans la description
     * @param pageable les informations de pagination
     * @return une page de tâches correspondantes
     */
    Page<Task> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
            String titleKeyword,
            String descriptionKeyword,
            Pageable pageable
    );

    /**
     * Retourne toutes les tâches ayant un statut donné.
     *
     * @param status le statut recherché
     * @return la liste des tâches correspondantes
     */
    List<Task> findByStatus(TaskStatus status);

    /**
     * Retourne toutes les tâches d'une catégorie donnée.
     *
     * @param category la catégorie recherchée
     * @return la liste des tâches correspondantes
     */
    List<Task> findByCategory(Category category);

    /**
     * Retourne toutes les tâches triées par date d'échéance croissante.
     *
     * @return la liste des tâches triées
     */
    List<Task> findAllByOrderByDueDateAsc();
}