package course.microservices.property.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import course.microservices.property.business.PropertyService;
import course.microservices.property.entities.Property;

import java.util.List;

public class PropertyLambda {

    private PropertyService service;

    public PropertyLambda() {
        service = new PropertyService();
    }

    public List<Property> handleRequest(Context context) {

        List<Property> props = service.getAllProperties();

        LambdaLogger logger = context.getLogger();
        logger.log("PropertyLambda invoked. Properties found: " + props.size());

        return props;

    }

}
