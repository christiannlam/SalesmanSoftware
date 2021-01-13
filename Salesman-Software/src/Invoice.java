import java.io.*;
import java.util.Scanner;
public class Invoice {
    
    private String customerName;
    private String customerAddress;
    private double price;
    private String product;
    private String deliveryOption;

    public Invoice() {

        customerName = "";
        customerAddress = "";
        price = 0.00;
        product = "";
        deliveryOption = "";
        
    }

    public Invoice(String customerName, String customerAddress, double price, String product, String deliveryOption) {

        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.price = price;
        this.product = product;
        this.deliveryOption = deliveryOption;
    }

    public String getCustomerName() {

        return customerName;
    }

    public String getCustomerAddress() {

        return customerAddress;
    }

    public double getPrice() {

        return price;
    }

    public String getProduct() {

        return product;
    }

    public String getDeliveryOption() {

        return deliveryOption;
    }

    public void setCustomerName(String customerName) {

        this.customerName = customerName;
    }

    public void setCustomerAddress(String customerAddress) {

        this.customerAddress = customerAddress;
    }

    public void setPrice(double price) {

        this.price = price;
    }

    public void setModel(String product) {

        this.product = product;
    }

    public void setDeliveryOption(String deliveryOption) {

        this.deliveryOption = deliveryOption;
    }

    public String toString() {
        return "Customer Name: " + customerName + " Customer Address: " + customerAddress + " Price: " + price + " Model: " + product + " Delivery Option: " + deliveryOption ;
    }
}