public class Product {
    
    private String name;
    private int quantity;
    private double price;
    private String model;

    public Product() {

        name = "";
        quantity = 0;
        price = 0.0;
        model = "";
    }

    public Product(String name, int quantity, double price, String model) {

        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.model = model;
    }

    public void setName(String n) {
        name = n;
    }

    public void setQuantity(int q) {
        quantity = q;
    }

    public void setPrice(double p) {
        price = p;
    }

    public void setModel(String m) {
        model = m;
    }

    public String getName() {

        return name;
    }

    public int getQuantity() {

        return quantity;
    }

    public double getPrice() {

        return price;
    }

    public String getModel() {

        return model;
    }

    public String toString() {

        return "Name: " + name + ", Quantity: " + quantity + ", Price: " + price + ", Model: " + model + "\n";
    }
}