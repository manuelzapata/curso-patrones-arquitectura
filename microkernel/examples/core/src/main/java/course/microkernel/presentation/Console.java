package course.microkernel.presentation;

import course.microkernel.business.DeliveryService;
import course.microkernel.business.ProductService;
import course.microkernel.common.entities.Delivery;
import course.microkernel.common.entities.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Console {

    private ProductService productService;
    private DeliveryService deliveryService;

    private Scanner scanner;

    public Console(){
        productService = new ProductService();
        deliveryService = new DeliveryService();
        scanner = new Scanner(System.in);
    }

    public void start() {

        int option;

        System.out.println("Aplicación de envíos");

        do {

            System.out.println();
            System.out.println("1. Calcular costo de envío.");
            System.out.println("2. Salir.");

            option = scanner.nextInt();

            switch (option) {
                case 1:
                    handleDeliveryCostOption();
                    break;
            }

        } while(option != 2);

        System.out.println("Aplicación terminada");
    }

    private void handleDeliveryCostOption(){

        //Mostrar producto para el cual se calculará el envío.
        List<Product> products = productService.getAll();

        System.out.println("Seleccione un producto: ");

        for (int index = 0; index < products.size(); index++) {

            Product product = products.get(index);
            System.out.println(index + ". " + product.getName());
        }

        //Seleccionar producto para el que se calculará el envío.
        int productIndex = scanner.nextInt();
        Product selectedProduct = products.get(productIndex);

        System.out.println("Distancia a donde se enviará el producto (kilómetros): ");
        double distance = scanner.nextDouble();

        //Leer salto de línea para que pueda pregunta por el código del país. (https://stackoverflow.com/a/13102066/1601530)
        scanner.nextLine();

        System.out.println("Código del país donde se entregará el producto: ");
        String countryCode = scanner.nextLine();

        //Creamos el objeto que será pasado a la capa de dominio para que se haga el cálculo.
        Delivery deliveryEntity = new Delivery(selectedProduct, distance, countryCode);

        try {

            double cost = deliveryService.calculateDeliveryCost(deliveryEntity);
            System.out.println("El costo del envío es " + cost);

        } catch (Exception exception) {
            System.out.println("No fue posible calcular el costo del envío. " + exception.getMessage());
        }



    }


}
