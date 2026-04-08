package fr.fms.mytodoapp.web.form;

import fr.fms.mytodoapp.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

/**
 * Objet de formulaire utilisé pour la création et la modification d'une tâche.
 *
 * <p>Cette classe n'est pas une entité JPA.
 * Elle sert uniquement à transporter les données entre la vue et le contrôleur.</p>
 *
 * @author Yassine ZRIBA
 * @version 1.0
 */
public class TaskForm {

    /**
     * Titre de la tâche.
     */
    @NotBlank(message = "Le titre est obligatoire.")
    @Size(max = 100, message = "Le titre ne doit pas dépasser 100 caractères.")
    private String title;

    /**
     * Description de la tâche.
     */
    @Size(max = 500, message = "La description ne doit pas dépasser 500 caractères.")
    private String description;

    /**
     * Date d'échéance de la tâche.
     */
    @NotNull(message = "La date d'échéance est obligatoire.")
    private LocalDate dueDate;

    /**
     * Statut de la tâche.
     */
    @NotNull(message = "Le statut est obligatoire.")
    private TaskStatus status;

    /**
     * Identifiant de la catégorie associée.
     *
     * <p>Peut être null si aucune catégorie n'est choisie.</p>
     */
    private Long categoryId;

    /**
     * Constructeur vide.
     */
    public TaskForm() {
    }

    /**
     * Retourne le titre.
     *
     * @return le titre
     */
    public String getTitle() {
        return title;
    }

    /**
     * Définit le titre.
     *
     * @param title le titre à définir
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retourne la description.
     *
     * @return la description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Définit la description.
     *
     * @param description la description à définir
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retourne la date d'échéance.
     *
     * @return la date d'échéance
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Définit la date d'échéance.
     *
     * @param dueDate la date d'échéance à définir
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Retourne le statut.
     *
     * @return le statut
     */
    public TaskStatus getStatus() {
        return status;
    }

    /**
     * Définit le statut.
     *
     * @param status le statut à définir
     */
    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    /**
     * Retourne l'identifiant de la catégorie.
     *
     * @return l'identifiant de la catégorie
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * Définit l'identifiant de la catégorie.
     *
     * @param categoryId l'identifiant de catégorie à définir
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
