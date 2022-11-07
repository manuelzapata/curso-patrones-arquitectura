package course.microservices.property.business;

import course.microservices.property.data_access.PropertyGateway;
import course.microservices.property.entities.Property;

import java.util.List;

/**
 * Contiene la lógica de negocio.
 * */
public class PropertyService {

    private PropertyGateway gateway;

    public PropertyService() {
        gateway = PropertyGateway.getInstance();
    }

    public List<Property> getAllProperties() {
        List<Property> properties = gateway.getAll();
        return properties;
    }


}
