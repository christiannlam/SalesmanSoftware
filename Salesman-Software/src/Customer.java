public class Customer {

    /** Name of customer */
    private String name;

    /** Customer's address */
    private String address;

    /** Sales tax */
    private double tax;

    /**
     * Default constructor
     */
    public Customer() {

        this.name = "";
        this.address = "";
        this.tax = 0.0;
    }

    /**
     * Overloaded constructor
     * @param name      name of customer
     * @param address   address of customer
     * @param tax       tax applied to customer's price
     */
    public Customer(String name, String address, double tax) {

        this.name = name;
        this.address = address;
        this.tax = tax;
    }

    /**
     * Get customer's name
     * @return  customer's name
     */
    public String getName() {

        return this.name;
    }

    /**
     * Get customer's address
     * @return  customer's address
     */
    public String getAddress() {

        return this.address;
    }

    /**
     * Get customer's tax
     * @return  customer's tax
     */
    public double getTax() {

        return this.tax;
    }

    /**
     * Set a customer's name
     * @param name  customer's new name
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * Set a customer's address
     * @param address   customer's new address
     */
    public void setAddress(String address) {

        this.address = address;
    }

    /**
     * Set a customer's tax
     * @param tax   customer's new tax
     */
    public void setTax(double tax) {

        this.tax = tax;
    }

    /**
     * Prints customer's information
     * @return  customer's information in a string
     */
    @Override
    public String toString() {

        return "Name: " + this.name + " Address: " + this.address + " Tax: " + (this.tax * 100) + "%";
    }
}