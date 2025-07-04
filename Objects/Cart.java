package Objects;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items;

    Cart(){
        this.items = new ArrayList<>();
    }

    public void addProduct(Product product, int quantity){
        if (product.getQuantity() < quantity)
            throw new IllegalArgumentException("Insufficient quantity in stock!");
        if (quantity < 0)
            throw new IllegalArgumentException("Quantity cannot be negative");
        if (product.isExpired())
            throw new IllegalArgumentException("Product is expired");

        CartItem item = new CartItem(product, quantity);
        items.add(item);
        product.decreaseQuantity(quantity);
    }

    public List<CartItem> getItems(){
        return items;
    }
    public boolean isEmpty(){
        return items.isEmpty();
    }
    public double getSubtotal(){
        double total = 0;
        for (CartItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }
}
