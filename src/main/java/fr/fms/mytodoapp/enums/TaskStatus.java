package fr.fms.mytodoapp.enums;

/**
 * Représente les statuts possibles d'une tâche dans l'application.
 *
 * <p>Un enum permet de limiter les valeurs autorisées
 * à une liste fermée et connue à l'avance.</p>
 *
 * <ul>
 *     <li>TO DO : la tâche est à faire</li>
 *     <li>IN_PROGRESS : la tâche est en cours</li>
 *     <li>DONE : la tâche est terminée</li>
 * </ul>
 *
 * @author - Yassine ZRIBA
 * @version 1.0
 */
public enum TaskStatus {

    /** Tâche à faire. */
    TODO,

    /** Tâche en cours de réalisation. */
    IN_PROGRESS,

    /** Tâche terminée. */
    DONE
}