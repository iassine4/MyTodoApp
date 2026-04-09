package fr.fms.mytodoapp.controller;

import fr.fms.mytodoapp.entity.Task;
import fr.fms.mytodoapp.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Contrôleur web dédié à la page d'accueil de l'application.
 *
 * <p>Cette page est destinée à présenter l'application
 * et à afficher quelques tâches d'exemple.</p>
 *
 * @author -
 * @version 1.0
 */
@Controller
public class HomeController {

    /**
     * Nombre maximum de tâches affichées sur la page d'accueil.
     */
    private static final int MAX_HOME_TASKS = 5;

    /**
     * Service métier utilisé pour récupérer les tâches.
     */
    private final TaskService taskService;

    /**
     * Constructeur avec injection de dépendance.
     *
     * @param taskService le service des tâches
     */
    public HomeController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Affiche la page d'accueil avec quelques tâches à venir.
     *
     * @param model modèle transmis à la vue
     * @return le nom de la vue à afficher
     */
    @GetMapping("/")
    public String showHomePage(Model model) {
        // Récupère toutes les tâches triées par date croissante
        List<Task> orderedTasks = taskService.getAllTasksOrderedByDueDate();

        // Limite l'affichage à quelques tâches pour garder une page d'accueil légère
        List<Task> featuredTasks = orderedTasks.stream()
                .limit(MAX_HOME_TASKS)
                .toList();

        model.addAttribute("appName", "MyTodoList");
        model.addAttribute("featuredTasks", featuredTasks);

        return "home";
    }
}