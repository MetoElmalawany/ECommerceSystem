package Managers;

import Interfaces.Shippable;
import Objects.*;

import java.util.ArrayList;
import java.util.List;

public class ECommerceServices {

    private static void printShippingNotice(List<Shippable> items) {
        double totalWeight = 0;
        System.out.println("\n** Shipment notice **");
        for (Shippable item : items) {
            System.out.print(item.getQuantity());
            System.out.printf("x %-15s%.0fg\n", item.getName(), item.getWeight()*item.getQuantity());
            totalWeight += item.getWeight() * item.getQuantity();
        }
        System.out.printf("\nTotal package weight: %.2fkg\n", totalWeight / 1000.0);
    }

    private static double shipmentFees(List<Shippable> items, Customer customer) {
        double fees = 0;
        for (Shippable item : items) {
            fees += 5 * (item.getWeight() / 1000);
        }
        if (customer.makePayment(fees)) {
            System.out.printf("Shipping Fees received for %.2f\n", fees);
        } else {
            customer.setBalance(customer.getBalance() + customer.getCart().getSubtotal());
            throw new IllegalStateException("Insufficient Balance! Your Payment has been refunded");
        }
        return fees;
    }

    public static void checkout(Customer customer) {
        Cart cart = customer.getCart();
        List<Shippable> itemsToShip = new ArrayList<>();
        double total = cart.getSubtotal();
        double shippingFees = 0;

        if (cart.isEmpty()) {
            throw new IllegalStateException("Can't Checkout an Empty cart!");
        }
        if (customer.makePayment(total)) {
            System.out.printf("Payment received for %.2f\n", total);
        } else {
            throw new IllegalStateException("Insufficient Balance!");
        }
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            if (product.isShippable()) {

                itemsToShip.add(new ShippableItem(product.getName(), product.getWeight(), item.getQuantity()));
            }
        }

        if (!itemsToShip.isEmpty()) {
            shippingFees = shipmentFees(itemsToShip,customer);
            printShippingNotice(itemsToShip);
        }

        System.out.println("\n** Checkout receipt **");
        for (CartItem item : cart.getItems()) {
            System.out.print(item.getQuantity());
            System.out.printf("x %-15s\t%.2f\n", item.getProduct().getName(), item.getTotalPrice());
        }
        System.out.println("---------------------------");
        System.out.printf("%-20s%.2f\n","SubTotal:", cart.getSubtotal());
        System.out.printf("%-20s%.2f\n","Shipping:", shippingFees);
        System.out.printf("%-20s%.2f\n","Amount:", shippingFees + cart.getSubtotal());
        System.out.printf("%-20s%.2f\n","Remaining Balance:", customer.getBalance() - (shippingFees + cart.getSubtotal()));


        // Validate: empty, expired, stock
        // Calculate: subtotal, shipping, total
        // Deduct balance
        // Print receipt
        // Call ShippingService if needed
    }

}
