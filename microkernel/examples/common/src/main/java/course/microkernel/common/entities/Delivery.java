package course.microkernel.common.entities;

public class Delivery {

    private Product product;
    private double distance;
    /**
     * Código del país donde será entregado el producto.
     * */
    private String countryCode;

    public Delivery(Product product, double distance, String countryCode) {
        this.product = product;
        this.distance = distance;
        this.countryCode = countryCode;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
