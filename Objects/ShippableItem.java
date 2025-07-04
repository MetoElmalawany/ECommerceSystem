package Objects;

import Interfaces.Shippable;

public class ShippableItem implements Shippable {
    private final String name;
    private final double weight;
    private final int quantity;

    public ShippableItem(String name, double weight, int quantity) {
        this.name = name;
        this.weight = weight;
        this.quantity = quantity;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public int getQuantity() { return quantity; }

}
