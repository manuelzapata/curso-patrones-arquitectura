package course.layers.data_access.tests;

import course.layers.data_access.active_record.PropertyActiveRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class PropertyActiveRecordTests {

    @Test
    void simpleActiveRecordTest() {


        //Creemos instancias de PropertyRowGateway para luego almacenarlas.
        PropertyActiveRecord propertyOne = new PropertyActiveRecord(1, "Apartamento de 2 habitaciones", 1, 5);
        PropertyActiveRecord propertyTwo = new PropertyActiveRecord(2, "Apartamento de 3 habitaciones y 2 baños", 1, 8);
        PropertyActiveRecord propertyThree = new PropertyActiveRecord(3, "Habitación con baño privado", 2, 2);

        //Guardar en base de datos.
        propertyOne.insert();
        propertyTwo.insert();
        propertyThree.insert();


        //Obtener propiedades usando el método estático de la clase.
        List<PropertyActiveRecord> properties = PropertyActiveRecord.getAll();

        //Deberían haber 3 propiedades registradas
        Assertions.assertEquals(3, properties.size());

        //Ahora actualicemos una de las propiedades.
        String propertyNewName = "Apartamento de 3 habitaciones";
        propertyTwo.setName(propertyNewName);
        propertyTwo.update();

        //Obtengamos la propiedad para ver si el nombre fue cambiado.
        PropertyActiveRecord updatedProperty = PropertyActiveRecord.getById(2);

        Assertions.assertNotNull(updatedProperty);
        Assertions.assertEquals(propertyNewName, updatedProperty.getName());

        //Eliminar un objeto
        propertyThree.delete();

        //Ahora deberían haber solo dos registros en la tabla Property
        properties = PropertyActiveRecord.getAll();
        Assertions.assertEquals(2, properties.size());

        //Si tratamos de obtener la propiedad por id, deberíamos obtener null.
        PropertyActiveRecord deletedProperty = PropertyActiveRecord.getById(3);
        Assertions.assertNull(deletedProperty);


    }
}
