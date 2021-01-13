import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Warehouse {
    
    /** Name of warehouse */
    private String name;

    /** Address of warehouse */
    private String address;

    /** City of warehouse */
    private String city;

    /** State of warehouse */
    private String state;

    /** Zipcode of warehouse */
    private String zip;

    /** Phone number of warehouse */
    private String phoneNumber;

    /** List of products the warehouse has */
    private ArrayList<Product> products = new ArrayList<Product>();

    /** The textfile with data of the products */
    private String productFile;

    /**
     * Constructor
     */
    public Warehouse() {

        name = "";
        address = "";
        city = "";
        state = "";
        zip = "";
        phoneNumber = "";
        productFile = "";
    }

    /**
     * Constructor
     * @param name  name of the warehouse
     */
    public Warehouse(String name, String address, String city, String state, String zip, String phoneNumber, String productFile) {

        String memAddress = FilePath.getMemAddress();
        String macAddress = "/Users/pengu/OneDrive/Documents/School/CECS 343/Salesman Software/src/mem/";

        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.productFile = productFile;

        try {
            Scanner read = new Scanner(new File(memAddress + this.productFile));

            while (read.hasNextLine()) {

                String line = read.nextLine();

                String[] details = line.split(";");

                int quantity = Integer.parseInt(details[1]);

                double price = Double.parseDouble(details[2]);

                Product product = new Product(details[0], quantity, price, details[3]);

                products.add(product);

            }

            read.close();

        } catch (FileNotFoundException fnf) {

            try {

                Scanner read = new Scanner(new File(macAddress + this.productFile));

                while (read.hasNext()) {

                    String line = read.nextLine();

                    String[] details = line.split(";");

                    int quantity = Integer.parseInt(details[1]);

                    double price = Double.parseDouble(details[2]);

                    Product product = new Product(details[0], quantity, price, details[3]);

                    products.add(product);
                }

                read.close();
            } catch (FileNotFoundException fnfm) {

                System.out.println("File not found");
            }
        }
    }

    public ArrayList<Product> getProductList() {

        return products;
    }

    public void addProduct(String name, int quantity, double price, String model) {

        Product newProduct = new Product(name, quantity, price, model);

        products.add(newProduct);

        try {

            PrintWriter write = new PrintWriter(productFile);

            for (Product i : products) {

                write.println(i.getName() + ";" + i.getQuantity() + ";" + i.getPrice() + ";" + i.getModel());
            }

            write.close();
        } catch (FileNotFoundException fnf) {

            System.out.println("File not found");
        }
    }

    public Product getProduct(String name) {

        for (Product i : products) {

            if (name == i.getName()) {

                return i;
            }
        }

        return null;
    }

    public String getWarehouseName() {

        return name;
    }

    public String getWarehouseAddress() {

        return address;
    }

    public String getWarehouseCity() {

        return city;
    }

    public String getWarehouseState() {

        return state;
    }

    public String getWarehouseZip() {

        return zip;
    }

    public String getWarehousePhoneNumber() {

        return phoneNumber;
    }

    public String getProductFile() {

        return productFile;
    }

    public void setWarehouseName(String name) {

        this.name = name;
    }

    public void setWarehouseAddress(String address) {

        this.address = address;
    }

    public void setWarehouseCity(String city) {

        this.city = city;
    }

    public void setWarehouseState(String state) {

        this.state = state;
    }

    public void setWarehouseZip(String zip) {

        this.zip = zip;
    }

    public void setWarehousePhoneNumber(String phone) {

        this.phoneNumber = phone;
    }

    public String toString() {

        return "Name: " + name + "\nAddress: " + address + ", City: " + city + ", State: " + state + " : " + zip + "\nPhone: " + phoneNumber + "\n";
    }
}