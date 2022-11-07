package course.layers.business.propertyManagement;

import course.layers.data_access.PropertyGateway;
import course.layers.entities.Property;

import java.util.ArrayList;
import java.util.List;

public class PropertyDomainModel {

    private static PropertyGateway gateway;

    static {
        gateway = PropertyGateway.getInstance();
    }

    private int id;
    private String name;
    private int type;
    private int maxGuests;

    private PropertyDomainModel(int id, String name, int type, int maxGuests) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.maxGuests = maxGuests;
    }

    public static List<PropertyDomainModel> getAllProperties() {

        List<Property> properties = gateway.getAll();
        List<PropertyDomainModel> result = new ArrayList<>();

        //Pasar los valores de los atributos a un nuevo objeto domain model.
        for(Property property: properties) {
            result.add(new PropertyDomainModel(property.getId(),
                                                property.getName(),
                                                property.getType(),
                                                property.getMaxGuests()));
        }

        return result;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public int getMaxGuests() {
        return maxGuests;
    }

}
