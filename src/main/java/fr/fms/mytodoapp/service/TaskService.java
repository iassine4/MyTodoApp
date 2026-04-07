package fr.fms.mytodoapp.service;

import fr.fms.mytodoapp.entity.Category;
import fr.fms.mytodoapp.entity.Task;
import fr.fms.mytodoapp.enums.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Définit les opérations métier liées à la gestion des tâches.
 *
 * <p>Cette interface appartient à la couche service de l'application.</p>
 *
 * <p>Elle sert d'intermédiaire entre la couche web
 * (controllers) et la couche d'accès aux données (repositories).</p>
 *
 * @author Yassine ZRIBA
 * @version 1.0
 */
public interface TaskService {

    /**
     * Retourne toutes les tâches avec pagination.
     *
     * @param pageable les informations de pagination
     * @return une page de tâches
     */
    Page<Task> getAllTasks(Pageable pageable);

    /**
     * Recherche les tâches à partir d'un mot-clé
     * dans le titre ou la description, avec pagination.
     *
     * @param keyword le mot-clé recherché
     * @param pageable les informations de pagination
     * @return une page de tâches correspondantes
     */
    Page<Task> searchTasks(String keyword, Pageable pageable);

    /**
     * Retourne une tâche à partir de son identifiant.
     *
     * @param id l'identifiant de la tâche
     * @return la tâche trouvée
     */
    Task getTaskById(Long id);

    /**
     * Enregistre une nouvelle tâche.
     *
     * @param task la tâche à enregistrer
     * @return la tâche enregistrée
     */
    Task saveTask(Task task);

    /**
     * Met à jour une tâche existante.
     *
     * @param id l'identifiant de la tâche à modifier
     * @param task les nouvelles données de la tâche
     * @return la tâche mise à jour
     */
    Task updateTask(Long id, Task task);

    /**
     * Supprime une tâche à partir de son identifiant.
     *
     * @param id l'identifiant de la tâche à supprimer
     */
    void deleteTaskById(Long id);

    /**
     * Retourne toutes les tâches d'un statut donné.
     *
     * @param status le statut recherché
     * @return la liste des tâches correspondantes
     */
    List<Task> getTasksByStatus(TaskStatus status);

    /**
     * Retourne toutes les tâches d'une catégorie donnée.
     *
     * @param category la catégorie recherchée
     * @return la liste des tâches correspondantes
     */
    List<Task> getTasksByCategory(Category category);

    /**
     * Retourne toutes les tâches triées par date d'échéance croissante.
     *
     * @return la liste triée des tâches
     */
    List<Task> getAllTasksOrderedByDueDate();
}