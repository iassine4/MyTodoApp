package fr.fms.mytodoapp.exception;

/**
 * Exception levée lorsqu'une ressource demandée n'existe pas.
 *
 * @author Yassine ZRIBA
 * @version 1.0
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Construit une exception avec un message explicite.
     *
     * @param message le message d'erreur
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}