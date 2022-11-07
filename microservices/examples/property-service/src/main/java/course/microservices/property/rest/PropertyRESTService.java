package course.microservices.property.rest;

import course.microservices.property.business.PropertyService;
import course.microservices.property.entities.Property;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Expone el servicio de propiedades para que pueda ser consumido a través de REST.
 * */
@RestController
@RequestMapping("/properties")
public class PropertyRESTService {

    private PropertyService service;

    public PropertyRESTService() {
        service = new PropertyService();
    }

    @GetMapping("")
    public List<Property> getAllProperties() {

        List<Property> props = service.getAllProperties();
        return props;

    }

}
