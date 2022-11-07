package course.layers.business.reservationManagement;

import course.layers.entities.Property;

class BedReservationRule implements ReservationRule {


    @Override
    public boolean couldCancel(Property property, int days) {

        boolean result = false;

        if(days > 3) {
            result = true;
        }

        return result;
    }
}
