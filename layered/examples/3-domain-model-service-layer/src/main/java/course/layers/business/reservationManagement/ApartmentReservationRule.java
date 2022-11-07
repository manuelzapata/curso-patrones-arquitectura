package course.layers.business.reservationManagement;

import course.layers.entities.Property;

class ApartmentReservationRule implements ReservationRule {

    @Override
    public boolean couldCancel(Property property, int days) {
        //Digamos que las reservas de apartamento nunca se pueden cancelar. Por eso siempre se retorna falso.
        return false;
    }

}
