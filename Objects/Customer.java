package Objects;

public class Customer {
    private String id;
    private String name;
    private double balance;
    private Cart cart;

    public Customer(String id, String name, double balance) {
        if (balance < 0)
            throw new IllegalArgumentException("Price cannot be negative");
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.cart = new Cart();
    }

    public boolean makePayment(double amount) {
        if (amount < 0)
            throw new IllegalArgumentException("Amount cannot be negative");
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void addItemToCart(Product product, int quantity) {
        if (quantity < 0)
            throw new IllegalArgumentException("Quantity cannot be negative");
        cart.addProduct(product, quantity);
    }

    public void setBalance(double balance) { this.balance = balance; }
    public Cart getCart() { return cart; }
    public double getBalance() { return balance; }
    public String getName() { return name; }
}
