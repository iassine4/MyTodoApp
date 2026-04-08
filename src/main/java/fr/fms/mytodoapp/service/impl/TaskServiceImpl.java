package fr.fms.mytodoapp.service.impl;

import fr.fms.mytodoapp.entity.Category;
import fr.fms.mytodoapp.entity.Task;
import fr.fms.mytodoapp.enums.TaskStatus;
import fr.fms.mytodoapp.exception.ResourceNotFoundException;
import fr.fms.mytodoapp.repository.TaskRepository;
import fr.fms.mytodoapp.service.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implémentation des opérations métier liées à la gestion des tâches.
 */
@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    /**
     * Constructeur avec injection de dépendance.
     *
     * @param taskRepository le repository des tâches
     */
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Retourne toutes les tâches avec pagination.
     *
     * @param pageable les informations de pagination
     * @return une page de tâches
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Task> getAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    /**
     * Recherche les tâches à partir d'un mot-clé dans le titre ou la description.
     *
     * @param keyword le mot-clé recherché
     * @param pageable les informations de pagination
     * @return une page de tâches correspondantes
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Task> searchTasks(String keyword, Pageable pageable) {
        if (keyword == null || keyword.isBlank()) {
            return taskRepository.findAll(pageable);
        }

        String trimmedKeyword = keyword.trim();

        return taskRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                trimmedKeyword,
                trimmedKeyword,
                pageable
        );
    }

    /**
     * Retourne une tâche à partir de son identifiant.
     *
     * @param id l'identifiant de la tâche
     * @return la tâche trouvée
     */
    @Override
    @Transactional(readOnly = true)
    public Task getTaskById(Long id) {
        return findTaskOrThrow(id);
    }

    /**
     * Enregistre une nouvelle tâche.
     *
     * @param task la tâche à enregistrer
     * @return la tâche enregistrée
     */
    @Override
    public Task saveTask(Task task) {
        if (task.getStatus() == null) {
            task.setStatus(TaskStatus.TODO);
        }

        return taskRepository.save(task);
    }

    /**
     * Met à jour une tâche existante.
     *
     * @param id l'identifiant de la tâche à modifier
     * @param updatedTask les nouvelles données
     * @return la tâche mise à jour
     */
    @Override
    public Task updateTask(Long id, Task updatedTask) {
        Task existingTask = findTaskOrThrow(id);

        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setDueDate(updatedTask.getDueDate());
        existingTask.setStatus(updatedTask.getStatus());
        existingTask.setCategory(updatedTask.getCategory());

        return taskRepository.save(existingTask);
    }

    /**
     * Supprime une tâche à partir de son identifiant.
     *
     * @param id l'identifiant de la tâche à supprimer
     */
    @Override
    public void deleteTaskById(Long id) {
        Task task = findTaskOrThrow(id);
        taskRepository.delete(task);
    }

    /**
     * Retourne toutes les tâches d'un statut donné.
     *
     * @param status le statut recherché
     * @return la liste des tâches correspondantes
     */
    @Override
    @Transactional(readOnly = true)
    public List<Task> getTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    /**
     * Retourne toutes les tâches d'une catégorie donnée.
     *
     * @param category la catégorie recherchée
     * @return la liste des tâches correspondantes
     */
    @Override
    @Transactional(readOnly = true)
    public List<Task> getTasksByCategory(Category category) {
        return taskRepository.findByCategory(category);
    }

    /**
     * Retourne toutes les tâches triées par date d'échéance croissante.
     *
     * @return la liste triée des tâches
     */
    @Override
    @Transactional(readOnly = true)
    public List<Task> getAllTasksOrderedByDueDate() {
        return taskRepository.findAllByOrderByDueDateAsc();
    }

    /**
     * Recherche une tâche par son identifiant ou lève une exception si elle n'existe pas.
     *
     * @param id l'identifiant recherché
     * @return la tâche trouvée
     */
    private Task findTaskOrThrow(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
    }
}