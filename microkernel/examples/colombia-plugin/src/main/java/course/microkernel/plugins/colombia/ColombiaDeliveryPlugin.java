package course.microkernel.plugins.colombia;

import course.microkernel.common.entities.Delivery;
import course.microkernel.common.entities.Product;
import course.microkernel.common.interfaces.DeliveryPlugin;

public class ColombiaDeliveryPlugin implements DeliveryPlugin {


    /**
     * El cálculo de Colombia es una mezcla de peso y distancia.
     * */
    public double calculateCost(Delivery delivery) {

        Product product = delivery.getProduct();
        double weight = product.getWeight();
        double distance = delivery.getDistance();

        double cost;

        if(weight <= 2) {

            cost = (distance <= 20) ? 3: 4;

        } else {

            cost = (distance <= 20) ? 4: 5;

        }

        return cost;

    }
}
