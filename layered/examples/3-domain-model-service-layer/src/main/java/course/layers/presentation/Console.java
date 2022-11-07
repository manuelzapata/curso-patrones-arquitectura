package course.layers.presentation;

import course.layers.business.propertyManagement.PropertyDomainModel;
import course.layers.entities.Property;
import course.layers.services.PropertyService;
import course.layers.services.ReservationService;

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
            List<PropertyDomainModel> properties;

            switch (option) {

                case 1: //Listar propiedades.
                    properties = propertyService.getAllProperties();

                    final String format = "Nombre: %s, Tipo: %s, Huéspedes: %d\n";

                    //Recorrer resultados
                    for(PropertyDomainModel property: properties) {

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

                    System.out.println("¿De qué propiedad deseas cancelar la reserva?");

                    properties = propertyService.getAllProperties();

                    //Recorrer resultados
                    for (int index = 0; index < properties.size(); index++) {
                        PropertyDomainModel property = properties.get(index);
                        System.out.println(index + ". " + property.getName());
                    }

                    int propertyIndex = scanner.nextInt();
                    PropertyDomainModel selectedProperty = properties.get(propertyIndex);
                    boolean result = reservationService.couldCancel(selectedProperty, days);

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
