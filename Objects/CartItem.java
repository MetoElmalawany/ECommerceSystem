package Objects;

public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        if (quantity < 0)
            throw new IllegalArgumentException("Quantity cannot be negative");
        this.product = product;
        this.quantity = quantity;
    }

    public double getTotalPrice() { return product.getPrice() * quantity; }

    public Product getProduct() { return product; }

    public int getQuantity() { return quantity; }
}
