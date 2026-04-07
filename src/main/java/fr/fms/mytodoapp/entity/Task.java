package fr.fms.mytodoapp.entity;

import fr.fms.mytodoapp.enums.TaskStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

/**
 * Représente une tâche à réaliser dans l'application.
 *
 * <p>Une tâche possède un titre, une description, une date d'échéance,
 * un statut et éventuellement une catégorie.</p>
 *
 * <p>Cette entité est persistée en base de données via JPA.</p>
 *
 * @author -
 * @version 1.0
 */
@Entity
@Table(name = "tasks")
public class Task {

    /**
     * Identifiant unique de la tâche.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Titre de la tâche.
     */
    @NotBlank(message = "Le titre est obligatoire.")
    @Size(max = 100, message = "Le titre ne doit pas dépasser 100 caractères.")
    private String title;

    /**
     * Description détaillée de la tâche.
     */
    @Size(max = 500, message = "La description ne doit pas dépasser 500 caractères.")
    private String description;

    /**
     * Date d'échéance de la tâche.
     */
    @NotNull(message = "La date d'échéance est obligatoire.")
    private LocalDate dueDate;

    /**
     * Statut courant de la tâche.
     */
    @NotNull(message = "Le statut est obligatoire.")
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    /**
     * Catégorie associée à la tâche.
     *
     * <p>Ce champ est optionnel, car une tâche peut ne pas être classée.</p>
     */
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    /**
     * Constructeur vide obligatoire pour JPA.
     */
    public Task() {
    }

    /**
     * Constructeur pratique pour créer une tâche complète.
     *
     * @param title le titre de la tâche
     * @param description la description de la tâche
     * @param dueDate la date d'échéance
     * @param status le statut de la tâche
     * @param category la catégorie associée
     */
    public Task(String title, String description, LocalDate dueDate, TaskStatus status, Category category) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
        this.category = category;
    }

    /**
     * Retourne l'identifiant de la tâche.
     *
     * @return l'identifiant de la tâche
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit l'identifiant de la tâche.
     *
     * @param id le nouvel identifiant
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retourne le titre de la tâche.
     *
     * @return le titre
     */
    public String getTitle() {
        return title;
    }

    /**
     * Définit le titre de la tâche.
     *
     * @param title le nouveau titre
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retourne la description de la tâche.
     *
     * @return la description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Définit la description de la tâche.
     *
     * @param description la nouvelle description
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
     * @param dueDate la nouvelle date d'échéance
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Retourne le statut de la tâche.
     *
     * @return le statut
     */
    public TaskStatus getStatus() {
        return status;
    }

    /**
     * Définit le statut de la tâche.
     *
     * @param status le nouveau statut
     */
    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    /**
     * Retourne la catégorie associée à la tâche.
     *
     * @return la catégorie
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Définit la catégorie associée à la tâche.
     *
     * @param category la nouvelle catégorie
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Retourne une représentation textuelle de la tâche.
     *
     * <p>On évite d'afficher ici tout l'objet catégorie complet
     * pour garder un affichage simple en debug.</p>
     *
     * @return une chaîne lisible pour le débogage
     */
    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", status=" + status +
                ", category=" + (category != null ? category.getName() : null) +
                '}';
    }
}