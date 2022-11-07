package course.layers.business.reservationManagement;

import course.layers.entities.Property;

class RoomReservationRule implements ReservationRule {

    @Override
    public boolean couldCancel(Property property, int days) {

        boolean result = false;

        /*Digamos que solo se puede cancelar si faltan 3 o más días para el inicio de la reserva
        * y son menos de 3 personas que se van a quedar.
        * */

        if(days > 3 && property.getMaxGuests() <= 2) {
            result = true;
        }

        return result;

    }

}
