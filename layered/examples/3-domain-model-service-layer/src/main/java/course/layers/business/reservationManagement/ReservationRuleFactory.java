package course.layers.business.reservationManagement;

public class ReservationRuleFactory {

    private static ReservationRuleFactory instance;

    private ReservationRuleFactory() {

    }

    public static ReservationRuleFactory getInstance() {

        if(instance == null) {
            instance = new ReservationRuleFactory();
        }

        return instance;

    }

    public ReservationRule getRule(int propertyType) {

        ReservationRule rule = null;

        switch (propertyType) {

            case 1: //apartamento.
                rule = new ApartmentReservationRule();
                break;
            case 2: //habitación.
                rule = new RoomReservationRule();
                break;
            case 3: //cama.
                rule = new BedReservationRule();
                break;

        }

        return rule;

    }

}
