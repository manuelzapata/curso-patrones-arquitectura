package course.event_driven.common;

import java.util.Date;

/**
 * Clase base para eventos. Contiene funcionalidad común para clases hijas.
 * */
public abstract class AbstractEvent implements Event {

    private Date eventDate;

    public AbstractEvent(Date eventDate) {
        this.eventDate = eventDate;
    }

    public Date getEventDate() {
        return eventDate;
    }

}