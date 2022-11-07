package course.microkernel.business;

import course.microkernel.common.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {

    public List<Product> getAll() {

        List<Product> products = new ArrayList<>();

        /*En un escenario normal, los productos vendrían de la capa de acceso a datos.
         * Para este ejemplo, se crearán objetos de prueba directamente aquí.
         * */
        Product productOne = new Product(1, "Play Station 4", 2.1, 26.5, 3.9, 28.8);
        Product productTwo = new Product(2, "Xbox One", 3.2, 27.4, 7.9, 33.3);
        Product productThree = new Product(2, "Wii U", 1.5, 17.2, 4.6, 26.9);


        products.add(productOne);
        products.add(productTwo);
        products.add(productThree);


        return products;
    }

}
