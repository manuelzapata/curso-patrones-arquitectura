package course.microkernel.common.interfaces;

import course.microkernel.common.entities.Delivery;

public interface DeliveryPlugin {

    /**
     * Establece el costo de envío en dólares.
     * */
    double calculateCost(Delivery delivery);

}
