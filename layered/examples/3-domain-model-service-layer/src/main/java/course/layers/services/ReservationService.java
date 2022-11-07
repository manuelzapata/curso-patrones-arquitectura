package course.layers.services;

import course.layers.business.propertyManagement.PropertyDomainModel;
import course.layers.business.reservationManagement.ReservationRule;
import course.layers.business.reservationManagement.ReservationRuleFactory;
import course.layers.entities.Property;

public class ReservationService {

    public boolean couldCancel(PropertyDomainModel propertyDomainModel, int days) {

        //Obtenemos la regla apropiada de acuerdo al tipo de propiedad.
        ReservationRule rule = ReservationRuleFactory.getInstance().getRule(propertyDomainModel.getType());

        Property property = new Property(propertyDomainModel.getId(),
                                            propertyDomainModel.getName(),
                                            propertyDomainModel.getType(),
                                            propertyDomainModel.getMaxGuests());

        return rule.couldCancel(property, days);

    }

}
