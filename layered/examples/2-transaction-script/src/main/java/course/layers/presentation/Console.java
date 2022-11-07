package course.layers.presentation;

import course.layers.business.PropertyService;
import course.layers.business.ReservationService;
import course.layers.entities.Property;

import java.util.List;
import java.util.Scanner;

public class Console {

    public void start() {

        PropertyService propertyService = new PropertyService();
        ReservationService reservationService = new ReservationService();

        int option;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Operaciones del sistema");

        do {

            System.out.println("1. Listar propiedades.");
            System.out.println("2. Ver si es posible cancelar reserva.");
            System.out.println("3. Salir.");

            option = scanner.nextInt();

            switch (option) {

                case 1: //Listar propiedades.
                    List<Property> properties = propertyService.getAllProperties();

                    final String format = "Nombre: %s, Tipo: %s, Huéspedes: %d\n";

                    //Recorrer resultados
                    for(Property property: properties) {

                        String typeAsString = "";
                        switch(property.getType()) {
                            case 1:
                                typeAsString = "Apartamento";
                                break;
                            case 2:
                                typeAsString = "Habitación";
                                break;
                            case 3:
                                typeAsString = "Cama";
                                break;
                        }

                        System.out.format(format, property.getName(),
                                typeAsString,
                                property.getMaxGuests());
                    }
                    System.out.println();

                    break;

                case 2: //Ver si es posible cancelar reserva.

                    System.out.println("¿Cuántos días faltan para el inicio de la reserva?");
                    int days = scanner.nextInt();

                    System.out.println("¿Qué tipo de propiedad es? (1 = Apartamento, 2 = Habitación, 3 = Cama)");
                    int propertyType = scanner.nextInt();

                    boolean result = reservationService.couldCancel(days, propertyType);
                    if(result) {
                        System.out.println("Se puede cancelar la reserva\n");
                    } else {
                        System.out.println("No se puede cancelar la reserva\n");
                    }


                    break;

            }

        } while (option != 3);

        System.out.println("Aplicación terminada");

    }

}
