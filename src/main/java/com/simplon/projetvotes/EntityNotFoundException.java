package com.simplon.projetvotes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {
    private final Object idObject;

    public EntityNotFoundException(Object idObject) {
        super();
        this.idObject = idObject;
    }

    public Object getIdObject() {
        return this.idObject;
    }
}
