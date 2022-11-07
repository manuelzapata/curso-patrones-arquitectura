package course.event_driven.common.events;

import course.event_driven.common.AbstractEvent;
import course.event_driven.entities.User;

import java.util.Date;

/**
 * Objeto que contiene la información básica de un evento
 * asociado a un usuario.
 * */
public class UserEvent extends AbstractEvent {

    private User user;
    private UserEventType type;

    public UserEvent(User user, UserEventType type, Date date) {
        super(date);
        this.user = user;
        this.type = type;
    }

    public User getUser(){
        return user;
    }

    public UserEventType getType() {
        return type;
    }

    /**
     * Tipo de evento relacionado con el usuario.
     * */
    public enum UserEventType {
        CREATED, UPDATED, DELETED
    }
}