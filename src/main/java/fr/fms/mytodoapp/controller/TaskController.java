package fr.fms.mytodoapp.controller;

import fr.fms.mytodoapp.web.form.TaskForm;
import fr.fms.mytodoapp.entity.Task;
import fr.fms.mytodoapp.enums.TaskStatus;
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

/**
 * Contrôleur web dédié à la gestion des tâches.
 *
 * <p>Cette classe reçoit les requêtes HTTP liées aux tâches,
 * appelle la couche service, puis transmet les données à la vue.</p>
 *
 * @author -
 * @version 1.0
 */
@Controller
@RequestMapping("/tasks")
public class TaskController {

    private static final String TASK_FORM_VIEW = "tasks/form";
    private static final String STATUSES_ATTRIBUTE = "statuses";
    private static final String FORM_ACTION_ATTRIBUTE = "formAction";
    private static final String PAGE_TITLE_ATTRIBUTE = "pageTitle";
    private static final String REDIRECT_TASKS = "redirect:/tasks";
    private static final String CREATE_PAGE_TITLE = "Créer une tâche";
    private static final String EDIT_PAGE_TITLE = "Modifier une tâche";

    /**
     * Service métier utilisé pour gérer les tâches.
     */
    private final TaskService taskService;

    /**
     * Constructeur avec injection de dépendance.
     *
     * @param taskService le service des tâches
     */
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Affiche la liste paginée des tâches, avec recherche optionnelle.
     *
     * @param keyword mot-clé de recherche optionnel
     * @param page numéro de page
     * @param size taille de la page
     * @param model modèle transmis à la vue
     * @return le nom de la vue à afficher
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

        return "tasks/list";
    }

    /**
     * Affiche le formulaire d'ajout d'une tâche.
     *
     * @param model modèle transmis à la vue
     * @return le nom de la vue du formulaire
     */
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        TaskForm taskForm = new TaskForm();
        taskForm.setStatus(TaskStatus.TODO);

        model.addAttribute("task", taskForm);
        model.addAttribute(STATUSES_ATTRIBUTE, TaskStatus.values());
        model.addAttribute(PAGE_TITLE_ATTRIBUTE, CREATE_PAGE_TITLE);
        model.addAttribute(FORM_ACTION_ATTRIBUTE, "/tasks/save");

        return TASK_FORM_VIEW;
    }

    /**
     * Enregistre une nouvelle tâche après validation du formulaire.
     *
     * @param taskForm données saisies dans le formulaire
     * @param bindingResult contient les erreurs de validation éventuelles
     * @param model modèle transmis à la vue
     * @return redirection vers la liste ou retour au formulaire en cas d'erreur
     */
    @PostMapping("/save")
    public String saveTask(
            @Valid @ModelAttribute("task") TaskForm taskForm,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(STATUSES_ATTRIBUTE, TaskStatus.values());
            model.addAttribute(PAGE_TITLE_ATTRIBUTE, CREATE_PAGE_TITLE);
            model.addAttribute(FORM_ACTION_ATTRIBUTE, "/tasks/save");
            return TASK_FORM_VIEW;
        }

        Task task = mapToTask(taskForm);
        taskService.saveTask(task);

        return REDIRECT_TASKS;
    }

    /**
     * Affiche le formulaire de modification d'une tâche existante.
     *
     * @param id identifiant de la tâche
     * @param model modèle transmis à la vue
     * @return le nom de la vue du formulaire
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Task task = taskService.getTaskById(id);
        TaskForm taskForm = mapToTaskForm(task);

        model.addAttribute("task", taskForm);
        model.addAttribute(STATUSES_ATTRIBUTE, TaskStatus.values());
        model.addAttribute(PAGE_TITLE_ATTRIBUTE, EDIT_PAGE_TITLE);
        model.addAttribute(FORM_ACTION_ATTRIBUTE, "/tasks/update/" + id);

        return TASK_FORM_VIEW;
    }

    /**
     * Met à jour une tâche après validation du formulaire.
     *
     * @param id identifiant de la tâche à modifier
     * @param taskForm données mises à jour
     * @param bindingResult contient les erreurs de validation éventuelles
     * @param model modèle transmis à la vue
     * @return redirection vers la liste ou retour au formulaire en cas d'erreur
     */
    @PostMapping("/update/{id}")
    public String updateTask(
            @PathVariable Long id,
            @Valid @ModelAttribute("task") TaskForm taskForm,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(STATUSES_ATTRIBUTE, TaskStatus.values());
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
     * @param id identifiant de la tâche à supprimer
     * @return redirection vers la liste des tâches
     */
    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return REDIRECT_TASKS;
    }

    /**
     * Convertit un objet de formulaire en entité Task.
     *
     * @param taskForm les données du formulaire
     * @return une entité Task
     */
    private Task mapToTask(TaskForm taskForm) {
        Task task = new Task();
        task.setTitle(taskForm.getTitle());
        task.setDescription(taskForm.getDescription());
        task.setDueDate(taskForm.getDueDate());
        task.setStatus(taskForm.getStatus());

        return task;
    }

    /**
     * Convertit une entité Task en objet de formulaire.
     *
     * @param task la tâche source
     * @return un objet TaskForm
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
}