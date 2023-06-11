package com.simplon.projetvotes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception levée lorsque l'entité recherchée n'est pas trouvée.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {
    private final Object idObject;

    /**
     * Constructeur de l'exception avec l'objet identifiant de l'entité.
     *
     * @param idObject l'objet identifiant de l'entité
     */
    public EntityNotFoundException(Object idObject) {
        super();
        this.idObject = idObject;
    }

    /**
     * Obtient l'objet identifiant de l'entité.
     *
     * @return l'objet identifiant de l'entité
     */
    public Object getIdObject() {
        return this.idObject;
    }
}


