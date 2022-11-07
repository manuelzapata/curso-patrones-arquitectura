package course.microservices.reservation.business;

public class ReservationService {

    public boolean couldCancel(int days, int propertyType) {

        boolean cancel = true;
        if(days < 3) {
            cancel = false;
        } else {
            //La reserva no se puede cancelar si es un apartamento.
            if(propertyType == 1) {
                cancel = false;
            }
        }

        return cancel;

    }


}
