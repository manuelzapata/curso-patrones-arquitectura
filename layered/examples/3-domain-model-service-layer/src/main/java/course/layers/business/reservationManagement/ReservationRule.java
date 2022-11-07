package course.layers.business.reservationManagement;

import course.layers.entities.Property;

public interface ReservationRule {

    boolean couldCancel(Property property, int days);

}
