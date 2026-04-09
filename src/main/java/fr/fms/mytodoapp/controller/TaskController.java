package fr.fms.mytodoapp.controller;

import fr.fms.mytodoapp.web.form.TaskForm;
import fr.fms.mytodoapp.entity.Category;
import fr.fms.mytodoapp.entity.Task;
import fr.fms.mytodoapp.enums.TaskStatus;
import fr.fms.mytodoapp.service.CategoryService;
import fr.fms.mytodoapp.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur web dédié à la gestion des tâches.
 *
 * <p>Cette classe reçoit les requêtes HTTP liées aux tâches,
 * appelle la couche service, puis transmet les données à la vue.</p>
 *
 * @author Yassine ZRIBA
 * @version 1.0
 */
@Controller
@RequestMapping("/tasks")
public class TaskController {

    private static final String TASK_FORM_VIEW = "tasks/form";
    private static final String TASK_LIST_VIEW = "tasks/list";
    private static final String TASK_ATTRIBUTE = "task";
    private static final String PAGE_TITLE_ATTRIBUTE = "pageTitle";
    private static final String FORM_ACTION_ATTRIBUTE = "formAction";
    private static final String REDIRECT_TASKS = "redirect:/tasks";
    private static final String CREATE_PAGE_TITLE = "Créer une tâche";
    private static final String EDIT_PAGE_TITLE = "Modifier une tâche";

    /**
     * Service métier des tâches.
     */
    private final TaskService taskService;

    /**
     * Service métier des catégories.
     */
    private final CategoryService categoryService;

    /**
     * Constructeur avec injection de dépendance.
     *
     * @param taskService service des tâches
     * @param categoryService service des catégories
     */
    public TaskController(TaskService taskService, CategoryService categoryService) {
        this.taskService = taskService;
        this.categoryService = categoryService;
    }

    /**
     * Ajoute automatiquement les statuts dans le modèle.
     *
     * @return tous les statuts possibles
     */
    @ModelAttribute("statuses")
    public TaskStatus[] populateStatuses() {
        return TaskStatus.values();
    }

    /**
     * Ajoute automatiquement les catégories dans le modèle.
     *
     * @return la liste des catégories triées par nom
     */
    @ModelAttribute("categories")
    public List<Category> populateCategories() {
        return categoryService.getAllCategoriesOrderedByName();
    }

    /**
     * Affiche la liste paginée des tâches, avec recherche optionnelle.
     *
     * @param keyword mot-clé de recherche
     * @param page numéro de page
     * @param size taille de page
     * @param model modèle transmis à la vue
     * @return la vue de liste
     */
    @GetMapping
    public String listTasks(
            @RequestParam(name = "keyword", defaultValue = "") String keyword,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size,
            Model model
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("dueDate").ascending());
        Page<Task> taskPage = taskService.searchTasks(keyword, pageable);

        model.addAttribute("taskPage", taskPage);
        model.addAttribute("tasks", taskPage.getContent());
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("totalPages", taskPage.getTotalPages());

        return TASK_LIST_VIEW;
    }

    /**
     * Affiche le formulaire d'ajout d'une tâche.
     *
     * @param model modèle transmis à la vue
     * @return la vue de formulaire
     */
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        TaskForm taskForm = new TaskForm();
        taskForm.setStatus(TaskStatus.TODO);

        model.addAttribute(TASK_ATTRIBUTE, taskForm);
        model.addAttribute(PAGE_TITLE_ATTRIBUTE, CREATE_PAGE_TITLE);
        model.addAttribute(FORM_ACTION_ATTRIBUTE, "/tasks/save");

        return TASK_FORM_VIEW;
    }

    /**
     * Enregistre une nouvelle tâche après validation.
     *
     * @param taskForm données du formulaire
     * @param bindingResult erreurs de validation éventuelles
     * @param model modèle transmis à la vue
     * @return redirection ou retour au formulaire
     */
    @PostMapping("/save")
    public String saveTask(
            @Valid @ModelAttribute(TASK_ATTRIBUTE) TaskForm taskForm,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(PAGE_TITLE_ATTRIBUTE, CREATE_PAGE_TITLE);
            model.addAttribute(FORM_ACTION_ATTRIBUTE, "/tasks/save");
            return TASK_FORM_VIEW;
        }

        Task task = mapToTask(taskForm);
        taskService.saveTask(task);

        return REDIRECT_TASKS;
    }

    /**
     * Affiche le formulaire de modification d'une tâche.
     *
     * @param id identifiant de la tâche
     * @param model modèle transmis à la vue
     * @return la vue de formulaire
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Task task = taskService.getTaskById(id);
        TaskForm taskForm = mapToTaskForm(task);

        model.addAttribute(TASK_ATTRIBUTE, taskForm);
        model.addAttribute(PAGE_TITLE_ATTRIBUTE, EDIT_PAGE_TITLE);
        model.addAttribute(FORM_ACTION_ATTRIBUTE, "/tasks/update/" + id);

        return TASK_FORM_VIEW;
    }

    /**
     * Met à jour une tâche existante après validation.
     *
     * @param id identifiant de la tâche
     * @param taskForm données du formulaire
     * @param bindingResult erreurs de validation éventuelles
     * @param model modèle transmis à la vue
     * @return redirection ou retour au formulaire
     */
    @PostMapping("/update/{id}")
    public String updateTask(
            @PathVariable Long id,
            @Valid @ModelAttribute(TASK_ATTRIBUTE) TaskForm taskForm,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(PAGE_TITLE_ATTRIBUTE, EDIT_PAGE_TITLE);
            model.addAttribute(FORM_ACTION_ATTRIBUTE, "/tasks/update/" + id);
            return TASK_FORM_VIEW;
        }

        Task task = mapToTask(taskForm);
        taskService.updateTask(id, task);

        return REDIRECT_TASKS;
    }

    /**
     * Supprime une tâche à partir de son identifiant.
     *
     * @param id identifiant de la tâche
     * @return redirection vers la liste
     */
    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return REDIRECT_TASKS;
    }

    /**
     * Convertit le DTO de formulaire en entité métier.
     *
     * @param taskForm les données du formulaire
     * @return l'entité Task correspondante
     */
    private Task mapToTask(TaskForm taskForm) {
        Task task = new Task();
        task.setTitle(taskForm.getTitle());
        task.setDescription(taskForm.getDescription());
        task.setDueDate(taskForm.getDueDate());
        task.setStatus(taskForm.getStatus());
        task.setCategory(resolveCategory(taskForm.getCategoryId()));
        return task;
    }

    /**
     * Convertit une entité Task en DTO de formulaire.
     *
     * @param task la tâche source
     * @return le DTO TaskForm
     */
    private TaskForm mapToTaskForm(Task task) {
        TaskForm taskForm = new TaskForm();
        taskForm.setTitle(task.getTitle());
        taskForm.setDescription(task.getDescription());
        taskForm.setDueDate(task.getDueDate());
        taskForm.setStatus(task.getStatus());

        if (task.getCategory() != null) {
            taskForm.setCategoryId(task.getCategory().getId());
        }

        return taskForm;
    }

    /**
     * Résout une catégorie à partir de son identifiant.
     *
     * @param categoryId identifiant de catégorie
     * @return la catégorie trouvée, ou null si aucun choix
     */
    private Category resolveCategory(Long categoryId) {
        if (categoryId == null) {
            return null;
        }
        return categoryService.getCategoryById(categoryId);
    }
}