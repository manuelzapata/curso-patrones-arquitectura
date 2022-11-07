package course.microservices.property.data_access;

import course.microservices.property.entities.Property;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Proporciona el acceso a datos para las propiedades.
 * */
public class PropertyGateway {

    private static PropertyGateway instance;

    private Connection connection;

    private PropertyGateway() {

    }

    public static PropertyGateway getInstance() {

        if(instance == null) {
            instance = new PropertyGateway();
        }
        return instance;
    }


    public List<Property> getAll() {

        List<Property> properties = new ArrayList<>();

        Property propertyOne = new Property(1, "Apartamento de 3 habitaciones en el sur de la ciudad", 1, 5);
        Property propertyTwo = new Property(2, "Habitación con cama doble", 2, 2);
        Property propertyThree = new Property(3, "Sofá cama en apartamento bonito", 3, 1);

        properties.add(propertyOne);
        properties.add(propertyTwo);
        properties.add(propertyThree);

        return properties;

    }

}
