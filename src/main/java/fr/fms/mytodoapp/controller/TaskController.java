package fr.fms.mytodoapp.controller;

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
        Task task = new Task();
        task.setStatus(TaskStatus.TODO);

        model.addAttribute("task", task);
        model.addAttribute("statuses", TaskStatus.values());
        model.addAttribute("pageTitle", "Créer une tâche");
        model.addAttribute("formAction", "/tasks/save");

        return "tasks/form";
    }

    /**
     * Enregistre une nouvelle tâche après validation du formulaire.
     *
     * @param task tâche saisie dans le formulaire
     * @param bindingResult contient les erreurs de validation éventuelles
     * @param model modèle transmis à la vue
     * @return redirection vers la liste ou retour au formulaire en cas d'erreur
     */
    @PostMapping("/save")
    public String saveTask(
            @Valid @ModelAttribute("task") Task task,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("statuses", TaskStatus.values());
            model.addAttribute("pageTitle", "Créer une tâche");
            model.addAttribute("formAction", "/tasks/save");
            return "tasks/form";
        }

        taskService.saveTask(task);
        return "redirect:/tasks";
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

        model.addAttribute("task", task);
        model.addAttribute("statuses", TaskStatus.values());
        model.addAttribute("pageTitle", "Modifier une tâche");
        model.addAttribute("formAction", "/tasks/update/" + id);

        return "tasks/form";
    }

    /**
     * Met à jour une tâche après validation du formulaire.
     *
     * @param id identifiant de la tâche à modifier
     * @param task données mises à jour
     * @param bindingResult contient les erreurs de validation éventuelles
     * @param model modèle transmis à la vue
     * @return redirection vers la liste ou retour au formulaire en cas d'erreur
     */
    @PostMapping("/update/{id}")
    public String updateTask(
            @PathVariable Long id,
            @Valid @ModelAttribute("task") Task task,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("statuses", TaskStatus.values());
            model.addAttribute("pageTitle", "Modifier une tâche");
            model.addAttribute("formAction", "/tasks/update/" + id);
            return "tasks/form";
        }

        taskService.updateTask(id, task);
        return "redirect:/tasks";
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
        return "redirect:/tasks";
    }
}