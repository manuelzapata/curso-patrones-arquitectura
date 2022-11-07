package course.microkernel.business;

import course.microkernel.common.entities.Delivery;
import course.microkernel.common.interfaces.DeliveryPlugin;
import course.microkernel.plugin_manager.DeliveryPluginManager;

import java.math.BigDecimal;

public class DeliveryService {

    public double calculateDeliveryCost(Delivery deliveryData) throws Exception {

        String countryCode = deliveryData.getCountryCode();
        DeliveryPluginManager manager = DeliveryPluginManager.getInstance();
        DeliveryPlugin plugin = manager.getDeliveryPlugin(countryCode);

        if(plugin == null) {
            throw new Exception("No hay un plugin disponible para el país indicado: " + countryCode);
        }

        return plugin.calculateCost(deliveryData);

    }

}
