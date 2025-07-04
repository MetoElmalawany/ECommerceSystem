import Managers.ECommerceServices;
import Objects.Customer;
import Objects.Product;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Customer customer = new Customer("1", "Meto", 1000.0);

        Product bread = new Product("Bread", 1, 10, true, LocalDate.of(2025,7,14), true, 100);
        Product biscuits = new Product("Biscuits", 10, 10,true, LocalDate.of(2025,7,14), true, 50);
        Product tv = new Product("Tv", 1000, 10, false, null, true, 10000);
        Product scratchCard = new Product("Scratch Card", 100, 10);

        customer.addItemToCart(scratchCard, 11);

        ECommerceServices.checkout(customer);

    }
}