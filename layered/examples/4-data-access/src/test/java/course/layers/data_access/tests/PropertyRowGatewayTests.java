package course.layers.data_access.tests;

import course.layers.data_access.row_gateway.PropertyFinder;
import course.layers.data_access.row_gateway.PropertyRowGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class PropertyRowGatewayTests {

    @Test
    void simpleRowGatewayTest() {

        //Obtener instancia de la clase que nos ayudará con las consultas.
        PropertyFinder finder = new PropertyFinder();

        //Creemos instancias de PropertyRowGateway para luego almacenarlas.
        PropertyRowGateway propertyOne = new PropertyRowGateway(1, "Apartamento de 2 habitaciones", 1, 5);
        PropertyRowGateway propertyTwo = new PropertyRowGateway(2, "Apartamento de 3 habitaciones y 2 baños", 1, 8);
        PropertyRowGateway propertyThree = new PropertyRowGateway(3, "Habitación con baño privado", 2, 2);

        //Guardar en base de datos.
        propertyOne.insert();
        propertyTwo.insert();
        propertyThree.insert();


        //Obtener propiedades usando el objeto finder
        List<PropertyRowGateway> properties = finder.getAll();

        //Deberían haber 3 propiedades registradas
        Assertions.assertEquals(3, properties.size());

        //Ahora actualicemos una de las propiedades.
        String propertyNewName = "Apartamento de 3 habitaciones";
        propertyTwo.setName(propertyNewName);
        propertyTwo.update();

        //Obtengamos la propiedad para ver si el nombre fue cambiado.
        PropertyRowGateway updatedProperty = finder.getById(2);

        Assertions.assertNotNull(updatedProperty);
        Assertions.assertEquals(propertyNewName, updatedProperty.getName());

        //Eliminar un objeto
        propertyThree.delete();

        //Ahora deberían haber solo dos registros en la tabla Property
        properties = finder.getAll();
        Assertions.assertEquals(2, properties.size());

        //Si tratamos de obtener la propiedad por id, deberíamos obtener null.
        PropertyRowGateway deletedProperty = finder.getById(3);
        Assertions.assertNull(deletedProperty);


    }

}
