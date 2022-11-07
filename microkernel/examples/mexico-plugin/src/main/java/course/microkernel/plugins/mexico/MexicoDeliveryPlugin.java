package course.microkernel.plugins.mexico;

import course.microkernel.common.entities.Delivery;
import course.microkernel.common.entities.Product;
import course.microkernel.common.interfaces.DeliveryPlugin;

public class MexicoDeliveryPlugin implements DeliveryPlugin {


    /**
     * Calcular el costo de envío de un producto de la tienda enviado dentro de México.
     * */
    public double calculateCost(Delivery delivery) {

        Product product = delivery.getProduct();

        double cost;

        //El peso del producto determina el costo.
        if(product.getWeight() <= 2) {

            cost = 5;

        } else {

            //El peso adicional después de 2 kg se cobra a 0.5 por kilo.
            cost = 5 + ( product.getWeight() - 2 ) * 0.5;

        }

        return cost;
    }
}
