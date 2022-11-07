package course.layers.services;

import course.layers.business.propertyManagement.PropertyDomainModel;

import java.util.List;

public class PropertyService {

    public List<PropertyDomainModel> getAllProperties() {

        List<PropertyDomainModel> properties = PropertyDomainModel.getAllProperties();
        return properties;
    }

}
