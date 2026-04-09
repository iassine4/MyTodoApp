package fr.fms.mytodoapp.enums;

/**
 * Représente les statuts possibles d'une tâche dans l'application.
 *
 * <p>Chaque statut possède :</p>
 * <ul>
 *     <li>une valeur technique utilisée dans le code et la base de données</li>
 *     <li>un libellé utilisateur affiché dans les vues</li>
 * </ul>
 *
 * @author Yassine ZRIBA
 * @version 1.0
 */
public enum TaskStatus {

    /**
     * Tâche à faire.
     */
    TODO("À faire"),

    /**
     * Tâche en cours.
     */
    IN_PROGRESS("En cours"),

    /**
     * Tâche terminée.
     */
    DONE("Terminée");

    /**
     * Libellé lisible affiché à l'utilisateur.
     */
    private final String label;

    /**
     * Construit un statut avec son libellé utilisateur.
     *
     * @param label le libellé affiché dans les vues
     */
    TaskStatus(String label) {
        this.label = label;
    }

    /**
     * Retourne le libellé utilisateur du statut.
     *
     * @return le libellé du statut
     */
    public String getLabel() {
        return label;
    }
}