package Objects;

import java.time.LocalDate;

public class Product {
    private final String name;
    private final double price;
    private int quantity;

    private final boolean expirable;
    private LocalDate expiryDate;

    private final boolean shippable;
    private double weightGrams;

    public Product(String name, double price, int quantity,
                   boolean expirable, LocalDate expiryDate,
                   boolean shippable, double weightGrams) {
        if (price < 0)
            throw new IllegalArgumentException("Price cannot be negative");
        if (quantity < 0)
            throw new IllegalArgumentException("Quantity cannot be negative");
        if (weightGrams < 0)
            throw new IllegalArgumentException("Weight cannot be negative");
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expirable = expirable;
        this.expiryDate = expiryDate;
        this.shippable = shippable;
        this.weightGrams = weightGrams;
    }

    public Product(String name, double price, int quantity) {
        this(name, price, quantity, false, null, false, 0.0);
    }

    public boolean isExpired() {
        return expirable && LocalDate.now().isAfter(expiryDate);
    }

    public boolean isShippable() {
        return shippable;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public double getWeight() { return weightGrams; }

    public void decreaseQuantity(int quantity) {
        if (quantity < 0)
            throw new IllegalArgumentException("Quantity cannot be negative");
        this.quantity -= quantity;
    }

}
