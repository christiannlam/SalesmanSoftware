public class Salesperson {
    private String name;
    private String email;
    private String phone;
    private double cRate;
    private int sales;

    /**
     * Default constructor
     */
    public Salesperson() {
        name = "";
        email = "";
        phone = "";
        cRate = 0;
        sales = 0;
    }

    /**
     * Overloaded constructor
     * @param n name of salesperson
     * @param e email of salesperson
     * @param p phone number of salesperson
     * @param r commission rate of salesperson
     * @param s total sales of salesperson
     */
    public Salesperson(String n, String e, String p, double r, int s) {
        name = n;
        email = e;
        phone = p;
        cRate = r;
        sales = s;
    }

    /**
     * Set the name of a salesperson
     * @param n new name of salesperson
     */
    public void setName(String n) {
        name = n;
    }

    /**
     * Set the email of a salesperson
     * @param e new email of salesperson
     */
    public void setEmail(String e) {
        email = e;
    }

    /**
     * Set the phone number of a salesperson
     * @param p new phone number of salesperson
     */
    public void setPhone(String p) {
        phone = p;
    }

    /**
     * Set the commission rate of a salesperson
     * @param c new commission rate of a salesperson
     */
    public void setcRate(double c) {
        cRate = c;
    }

    /**
     * Set the sales of a salesperson
     * @param s new sales of a salesperson
     */
    public void setSales(int s) {
        sales = s;
    }

    /**
     * Get the name of the salesperson
     * @return  name of salesperson
     */
    public String getName() {
        return name;
    }

    /**
     * Get the email of the salesperson
     * @return  email of salesperson
     */
    public String getEmail() {
        return email;
    }

    /**
     * Get the phone number of the salesperson
     * @return  phone number of salesperson
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Get commission rate of the salesperson
     * @return  commission rate of salesperson
     */
    public double getcRate() {
        return cRate;
    }

    /**
     * Get the sales of the salesperson
     * @return  sales of salesperson
     */
    public int getSales() {
        return sales;
    }

    /**
     * Prints out data of the salesperson in a nice format
     */
    @Override
    public String toString() {
        return "Name: " + name + " Phone: " + phone + " Email: " + email + " Commission Rate: " + (cRate * 100.0) + "%" + " Sales: " + sales ;
    }

}