package course.layers.data_access.tests;

import course.layers.data_access.table_gateway.PropertyTableGateway;
import course.layers.entities.Property;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class PropertyTableGatewayTests {

    @Test
    void simpleTableGatewayTest() {

        //Obtener instancia del gateway
        PropertyTableGateway gatewayInstance = PropertyTableGateway.getInstance();

        //Insertemos 3 propiedades
        gatewayInstance.insert(1, "Apartamento de 2 habitaciones", 1, 5);
        gatewayInstance.insert(2, "Apartamento de 3 habitaciones y 2 baños", 1, 8);
        gatewayInstance.insert(3, "Habitación con baño privado", 2, 2);

        //Obtener propiedades
        List<Property> properties = gatewayInstance.getAll();

        //Deberían haber 3 propiedades registradas
        Assertions.assertEquals(3, properties.size());

        //Ahora actualicemos una de las propiedades.
        String propertyNewName = "Apartamento de 3 habitaciones";
        gatewayInstance.update(2, propertyNewName, 1, 8);

        //Obtengamos la propiedad para ver si el nombre fue cambiado.
        Property updatedProperty = gatewayInstance.getById(2);

        Assertions.assertNotNull(updatedProperty);
        Assertions.assertEquals(propertyNewName, updatedProperty.getName());

        //Eliminar un objeto
        gatewayInstance.delete(3);

        //Ahora deberían haber solo dos objetos de tipo Property
        properties = gatewayInstance.getAll();
        Assertions.assertEquals(2, properties.size());

        //Si tratamos de obtener la propiedad por id, deberíamos obtener null.
        Property deletedProperty = gatewayInstance.getById(3);
        Assertions.assertNull(deletedProperty);


    }

}
