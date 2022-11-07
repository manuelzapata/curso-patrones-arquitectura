package course.layers.business;

import course.layers.data_access.PropertyGateway;
import course.layers.entities.Property;

import java.util.List;

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
