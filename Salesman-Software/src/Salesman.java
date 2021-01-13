import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.io.File;
import java.io.IOException;

public class Salesman {

    public static void main (String[] args) {

        String memAddress = FilePath.getMemAddress();

        if (login()) {

            ArrayList<Warehouse> warehouses = readWarehouse();

            ArrayList<Product> allProducts = getAllProducts(warehouses);

            ArrayList<Salesperson> employee = readSalesperson();

            ArrayList<Customer> customers = readCustomer();
            
            ArrayList<Invoice> open = readOpen();
            
            ArrayList<Invoice> closed = readClosed();
            
            // Creates a scanner and variable to hold user's input
            Scanner scan = new Scanner(System.in);

            int choice;

            int menuInput;

            do {

                // Prints main menu
                System.out.println("");
                System.out.print("\033[H\033[2J");
                printMenu();

                // Get user's choice and validate it
                choice = scan.nextInt();
                choice = validation(1, 6, choice);

                // Show products menu
                while (choice == 1) {

                    System.out.println("");
                    for (Warehouse house : warehouses) {

                        ArrayList<Product> productList = house.getProductList();

                        for (Product j : productList) {

                            System.out.print(j);
                        }
                    }
                        // Prints products menu
                    System.out.println("");
                    productMenu();

                    // Get user's choice and validate it
                    menuInput = scan.nextInt();
                    menuInput = validation(1, 3, menuInput);
                    System.out.print("\033[H\033[2J");
                    // Show low inventory
                    if (menuInput == 1) {   
                        boolean isLow = false;

                        // User picks a warehouse to show products low in stock
                        System.out.println("\nSelect a warehouse: ");
                        for (int i = 0; i < warehouses.size(); i++) {
                            System.out.print((i + 1) + ". " + warehouses.get(i));
                        }
                        System.out.print("Selection: ");
                        menuInput = scan.nextInt();

                        // Gets product list for warehouse
                        warehouses.get(menuInput - 1).getProductList();
                        System.out.println("\nWarehouse: " + warehouses.get(menuInput - 1).getWarehouseName());

                        // If a product has 5 or less in stock, print it out
                        for(Product i : warehouses.get(menuInput - 1).getProductList())
                        {
                            if( i.getQuantity() <= 5 )
                            {
                                System.out.println("\nLow Inventory For: " + i.getName());
                                isLow = true;
                            }
                            
                        }
                        if (!isLow) {
                            System.out.println("\nNo Product Low In Inventory.");
                        }
                    }
                    else if (menuInput == 2){
                        System.out.println("\nSelect a warehouse: ");

                        for (int i = 0; i < warehouses.size(); i++) {
                            System.out.print((i + 1) + ". " + warehouses.get(i));
                        }

                        // User chooses a warehouse
                        System.out.print("Selection: ");
                        menuInput = scan.nextInt();

                        System.out.println("\nAdd A New Product To Warehouse: " + warehouses.get(choice - 1).getWarehouseName());

                        // Clears input queue
                        scan.nextLine();

                        // User enters name of new product name
                        System.out.println("Name of Product: ");
                        String name = scan.nextLine();

                        // User enters quantity available
                        System.out.println("Quantity of Product: ");
                        int quantity = scan.nextInt();

                        // Clears input queue
                        scan.nextLine();

                        // User enters price of the product
                        System.out.println("Price of Product: ");
                        double price = scan.nextDouble();

                        // Clears input queue
                        scan.nextLine();

                        // User enters model of the product
                        System.out.println("Model of Product: ");
                        String model = scan.nextLine(); 

                        // Adds product to the warehouse
                        warehouses.get(choice - 1).addProduct(name, quantity, price, model);
                    }

                    // If user chooses 3, exit out of Show Products menu
                    choice = menuInput == 3 ? 0 : choice;
                }

                // Warehouse management menu
                while (choice == 2) {

                    // Prints warehouse menu
                    System.out.println("");
                    warehouseMenu();

                    // Get user's choice and validate it
                    menuInput = scan.nextInt();
                    menuInput = validation(1, 4, menuInput);

                    // add new warehouse
                    if (menuInput == 1) {
                        // Clear input queue
                        scan.nextLine();
                        
                        System.out.println("\nCreate new warehouse");

                        // User inputs warehouse's name
                        System.out.print("Enter warehouse name: ");
                        String name = scan.nextLine();

                        // User inputs warehouse's address
                        System.out.print("Enter warehouse address: ");
                        String address = scan.nextLine();

                        // User inputs warehouse's city
                        System.out.print("Enter warehouse city: ");
                        String city = scan.nextLine();
                
                        // User inputs warehouse's state
                        System.out.print("Enter warehouse state: ");
                        String state = scan.nextLine();                        
                       
                        // User inputs warehouse's zip
                        System.out.print("Enter warehouse zip code: ");
                        String zip = scan.nextLine();  

                        // User inputs warehouse's phone number
                        System.out.print("Enter warehouse phone number: ");
                        String phone = scan.nextLine();
                        
                        // User inputs warehouse's name for product files
                        System.out.print("Enter product file name for new warehouse: ");
                        String pfile = scan.nextLine();

                        boolean duplicate = false;
                        for (Warehouse i : warehouses) {

                            if (address.equals(i.getWarehouseAddress()) && phone.equals(i.getWarehousePhoneNumber())) {

                                System.out.println("This warehouse already exists");

                                duplicate = true;
                            }
                        }

                        if (!duplicate) {

                            try {

                                File productFile = new File(memAddress + pfile + ".txt");

                                if (!productFile.createNewFile()) {

                                    System.out.println("File exists");
                                }
                            } catch (IOException e) {

                                try {

                                    File productFile = new File("/Users/pengu/OneDrive/Documents/School/CECS 343/Salesman Software/src/mem/" + pfile + ".txt");

                                    if (!productFile.createNewFile()) {

                                        System.out.println("File exists");
                                    }
                                } catch (IOException ee) {

                                    System.out.println("Error creating file");
                                }
                            }
                        }

                        warehouses.add(new Warehouse(name, address, city, state, zip, phone, pfile + ".txt"));

                        writeWarehouse(warehouses);
                    }

                    else if (menuInput == 2) {

                        System.out.println("");

                        for (Warehouse i : warehouses) {

                            System.out.println(i.getWarehouseName() + ": ");

                            ArrayList<Product> productList = i.getProductList();

                            for (Product j : productList) {

                                System.out.println(j.getName() + ": " + j.getQuantity());
                            }

                            System.out.println("");
                        }
                    }

                    else if (menuInput == 3) {

                        System.out.println("");

                        for (int i = 0; i < warehouses.size(); i++) {

                            System.out.println(i + 1 + ". " + warehouses.get(i));
                        }

                        System.out.print("Selection: ");

                        menuInput = scan.nextInt() - 1;
                        menuInput = validation(0, warehouses.size() - 1, menuInput);

                        ArrayList<Product> storage = warehouses.get(menuInput).getProductList();

                        for (int i = 0; i < storage.size(); i++) {

                            System.out.println(i + 1 + ". " + storage.get(i));
                        }

                        System.out.print("Choose a product to restock: ");

                        menuInput = scan.nextInt() - 1;
                        menuInput = validation(0, storage.size() - 1, menuInput);

                        System.out.print("Amount added to stock: ");

                        int restock = scan.nextInt();
                        restock = validation(1, Integer.MAX_VALUE, restock);

                        storage.get(menuInput).setQuantity(restock);
                    }

                    choice = menuInput == 4 ? 0 : choice;
                }

                while (choice == 3){
                    System.out.println("\nCurrent Open Invoices: ");
                        if(open.size() == 0)
                        {
                            System.out.println("No Open Invoices ATM: ");
                        }
                        else{
                            for (Invoice i : open) {
                                System.out.println(i);
                            }
                    }
                    System.out.println("");
                    openInvoiceMenu();
                    menuInput = scan.nextInt();
                    menuInput = validation(1, 3, menuInput);
                    if(menuInput == 1)
                    {
                        System.out.println("\nSelect Customer ");

                        for (int i = 0; i < customers.size(); i++) {

                            System.out.println(i + 1 + ". " + customers.get(i));
                        }
                        System.out.print("Selection: ");
                        menuInput = scan.nextInt() - 1;
                        menuInput = validation(0, customers.size() - 1, menuInput);
                        Customer customer = customers.get(menuInput);

                        System.out.println("");

                        for (int i = 0; i < allProducts.size(); i++) {

                            System.out.println(i + 1 + ". " + allProducts.get(i));
                        }
                        System.out.print("What is the customer buying: ");
                        menuInput = scan.nextInt() - 1;
                        menuInput = validation(0, allProducts.size() - 1, menuInput);
                        Product cart = allProducts.get(menuInput);

                        System.out.print("\nDelivery method:\n"
                            + "1. Shipping\n"
                            + "2. Pickup\n"
                            + "Selection: ");
                        menuInput = scan.nextInt();
                        menuInput = validation(1, 2, menuInput);

                        if (menuInput == 1) {

                            open.add(new Invoice(customer.getName(), customer.getAddress(), cart.getPrice() + (cart.getPrice() * customer.getTax()), cart.getName(), "Shipping"));
                        }
                        else {

                            open.add(new Invoice(customer.getName(), customer.getAddress(), cart.getPrice() + (cart.getPrice() * customer.getTax()), cart.getName(), "Pickup"));
                        }
                        
                        writeOpenInvoices(open);
                    }

                    else if (menuInput == 2) {

                        System.out.println("");
                        scan.nextLine();

                        System.out.print("Enter customer's name: ");
                        String name = scan.nextLine();

                        System.out.print("Enter customer's address: ");
                        String address = scan.nextLine();

                        System.out.print("Enter customer's sales tax as whole %: ");
                        int percentage = scan.nextInt();
                        double tax = percentage / 100.0;

                        customers.add(new Customer(name, address, tax));

                        writeCustomer(customers);
                    }
                    choice = menuInput == 3 ? 0 : choice; 
                } 

                while(choice == 4)
                {
                    System.out.println("\nClosed Invoices: ");
                    for( Invoice i : closed)
                    {
                        System.out.println(i);
                    }
                    System.out.println("");
                    closedInvoicesMenu();
                    menuInput = scan.nextInt();
                    menuInput = validation(1, 1, menuInput);
                    choice = menuInput == 1 ? 0 : choice;
                }

                while (choice == 5)
                {

                    System.out.println("");
                    for (Salesperson i : employee) {

                        System.out.println(i);
                    }

                    System.out.println("");
                    totalSalesMenu();
                    menuInput = scan.nextInt();
                    menuInput = validation(1, 4, menuInput);

                    // add new employee
                    if (menuInput == 1) {

                        // Clear input queue
                        scan.nextLine();

                        System.out.println("\nCreate new employee");

                        // User inputs employee's name
                        System.out.print("Enter employee name: ");
                        String name = scan.nextLine();

                        // User inputs employee's email
                        System.out.print("Enter employee email: ");
                        String email = scan.nextLine();

                        // User inputs employee's phone number
                        System.out.print("Enter employee phone number: ");
                        String phone = scan.nextLine();

                        // Add new employee to the arraylist
                        employee.add(new Salesperson(name, email, phone, 0.10, 0));

                        writeSalesperson(employee);
                    }

                    else if (menuInput == 2) {

                        System.out.println("\nRemove employee");

                        // User selects from list of employees
                        for (int i = 0; i < employee.size(); i++) {

                            System.out.println(i + 1 + " " + employee.get(i));
                        }

                        System.out.print("\nSelect an employee to remove: ");


                        menuInput = scan.nextInt() - 1;
                        menuInput = validation(0, employee.size() - 1, menuInput);

                        // Remove employee at that index
                        employee.remove(menuInput);

                        writeSalesperson(employee);
                    }
                    else if (menuInput == 3) {

                        System.out.println("\nChange commission rate");

                        System.out.print("1. Change all salespeople's commission rates\n"
                            + "2. Change one salesperson's commission rate\n"
                            + "3. Back\n"
                            + "Selection: ");

                        menuInput = scan.nextInt();

                        if (menuInput == 1) {

                            System.out.print("\nEnter new commission rate as whole %: ");

                            int percentage = scan.nextInt();

                            double rate = percentage / 100.0;

                            while (rate < 0.0) {

                                System.out.print("Invalid input. Try again: ");

                                percentage = scan.nextInt();
                                rate = percentage / 100.0;
                            }

                            for (Salesperson i : employee) {

                                i.setcRate(rate);
                            }

                            writeSalesperson(employee);
                        }
                        else if (menuInput == 2) {

                            System.out.println("");

                            for (int i = 0; i < employee.size(); i++) {

                                System.out.println(i + 1 + ". " + employee.get(i));
                            }

                            System.out.print("Selection: ");

                            menuInput = scan.nextInt() - 1;
                            menuInput = validation(0, employee.size() - 1, menuInput);

                            System.out.print("\nEnter new commission rate as whole %: ");

                            int percentage = scan.nextInt();
                            double rate = percentage / 100.0;

                            while (rate < 0.0) {

                                System.out.print("Invalid input. Try again: ");

                                percentage = scan.nextInt();
                                rate = percentage / 100.0;
                            }

                            employee.get(menuInput).setcRate(rate);

                            writeSalesperson(employee);
                        }
                    }

                    choice = menuInput == 4 ? 0 : choice;
                }
                    
            } while (choice != 6);
        }
    }

    /**
     * Prints out the main menu
     */
    public static void printMenu() {

        System.out.print(
                "====================================\n" +
                "Main Menu\n" +
                "====================================\n"
            + "1. [Show products]\n"
            + "2. [Warehouse Management]\n"
            + "3. [Show open invoices]\n"
            + "4. [Show closed invoices]\n"
            + "5. [Show total sales and commissions]\n"
            + "6. [Close]\n"
            + "Selection: ");
    }

    /**
     * Prints out the product menu
     */
    public static void productMenu() {

        System.out.print("====================================\n" +
                "Show Products\n" +
                "====================================\n"
            + "1. [Show low inventory]\n"
            + "2. [Add new model]\n"
            + "3. [Back]\n"
            + "Selection: ");
    }

    /**
     * Prints out the warehouse menu
     */
    public static void warehouseMenu() {

        System.out.print("====================================\n" +
                "Warehouse Management\n" +
                "====================================\n"
            + "1. [Add new warehouse]\n"
            + "2. [Show quantity of each warehouse]\n"
            + "3. [Add inventory items to a warehouse]\n"
            + "4. [Back]\n"
            + "Selection: ");
    }

    public static void openInvoiceMenu()
    {
        System.out.print("====================================\n" +
                "Show Open Invoices\n" +
                "====================================\n"
            + "1. [Create Open Invoice]\n"
            + "2. [Create new customer]\n"
            + "3. [Back]\n"
            + "Selection: ");

    }

    public static void totalSalesMenu()
    {
        System.out.print("====================================\n" +
                "Show Total Sales and Commissions\n" +
                "====================================\n"
            + "1. [Add Employee]\n"
            + "2. [Remove Employee]\n"
            + "3. [Change Commission Rate]\n"
            + "4. [Back]\n"
            + "Selection: ");
    }

    public static void closedInvoicesMenu()
    {
        System.out.print("====================================\n" +
                "Show Closed Invoices\n" +
                "====================================\n"
            + "1. [Back]\n"
            + "Selection: ");
    }

    /**
     * Validates user input to ensure it's valid
     * @param min       the lowest option user is allowed to choose
     * @param max       the max option user is allowed to choose
     * @param choice    the user's choice
     * @return          user's choice
     */
    public static int validation(int min, int max, int choice) {

        // Create a scanner
        Scanner scan = new Scanner(System.in);

        // If choice is out of range, keep asking them till input is valid
        while (choice < min || choice > max) {

            System.out.print("Invalid input. Try again: ");

            choice = scan.nextInt();
        }

        // Return their input
        return choice;
    }

    /**
     * Login prompt for user
     * @return  true if correct password
     */
    public static boolean login() {

        // Store the user
        ArrayList<User> owner = new ArrayList<>();

        Scanner scan = new Scanner(System.in);

        String memAddress = FilePath.getMemAddress();

        try {

            // Read file
            Scanner read = new Scanner(new File(memAddress +  "user.txt"));

            // If the file has user's information, read it
            if (read.hasNext()) {

                String line = read.nextLine();

                String[] tokens = line.split(";");

                // Create user
                User user = new User(tokens[0], tokens[1]);

                owner.add(user);
            }
            else {

                // Create a new user if there is none and write it to the file

                System.out.println( "====================================\n" +
                        "        [SALESMAN SOFTWARE]\n"
                        + "           [LOGIN SCREEN]       \n"
                        + "====================================");

                System.out.print("Enter username: ");

                String username = scan.nextLine();

                System.out.print("Enter password: ");

                String password = scan.nextLine();

                try {

                    PrintWriter write = new PrintWriter(memAddress + "user.txt");

                    write.println(username + ";" + password);

                    write.close();
                } catch (FileNotFoundException fnf) {

                    System.out.println("File not found Windows version");
                }
            }
        } catch (FileNotFoundException fnf) {

            try {

                Scanner read = new Scanner(new File("/Users/pengu/OneDrive/Documents/School/CECS 343/Salesman Software/src/mem/user.txt"));

                // Same as above
                if (read.hasNext()) {

                    String line = read.nextLine();
    
                    String[] tokens = line.split(";");
    
                    User user = new User(tokens[0], tokens[1]);

                    owner.add(user);
                }
                else {

                    System.out.println( "====================================\n" +
                            "        [SALESMAN SOFTWARE]\n"
                            + "           [LOGIN SCREEN]       \n"
                            + "====================================");
    
                    System.out.print("Enter username: ");
    
                    String username = scan.nextLine();
    
                    System.out.print("Enter password: ");
    
                    String password = scan.nextLine();
    
                    try {
    
                        PrintWriter write = new PrintWriter("/Users/pengu/OneDrive/Documents/School/CECS 343/Salesman Software/src/mem/user.txt");
    
                        write.println(username + ";" + password);
    
                        write.close();
                    } catch (FileNotFoundException fnfm) {
    
                        System.out.println("File not found Mac version");
                    }
                }
            } catch (FileNotFoundException fnfm) {

                System.out.println("File not found Mac version");
            }
        }

        System.out.println( "====================================\n" +
                            "        [SALESMAN SOFTWARE]\n"
                            + "           [LOGIN SCREEN]       \n"
                            + "====================================");

        // Prompt for password
        System.out.print("Enter password: ");

        String attempt = scan.nextLine();

        // If password is incorrect, keep trying
        while (!attempt.equals(owner.get(0).getPassword())) {

            System.out.print("Wrong password.\n"
                + "Enter password: ");

            attempt = scan.nextLine();
        }

        return true;
    }

    public static void writeWarehouse(ArrayList<Warehouse> warehouse) {

        String memAddress = FilePath.getMemAddress();

        try {

            PrintWriter write = new PrintWriter(memAddress + "warehouselist.txt");

            for (Warehouse i : warehouse) {

                write.println(i.getWarehouseName() + ";" + i.getWarehouseAddress() + ";" + i.getWarehouseCity() + ";" + i.getWarehouseState() + ";" + i.getWarehouseZip() + ";"
                    + i.getWarehousePhoneNumber() + ";" + i.getProductFile());
            }

            write.close();

        } catch (FileNotFoundException fnf) {

            try {

                PrintWriter write = new PrintWriter("/Users/pengu/OneDrive/Documents/School/CECS 343/Salesman Software/src/mem/warehouselist.txt");

                for (Warehouse i : warehouse) {

                    write.println(i.getWarehouseName() + ";" + i.getWarehouseAddress() + ";" + i.getWarehouseCity() + ";" + i.getWarehouseState() + ";" + i.getWarehouseZip() + ";"
                    + i.getWarehousePhoneNumber() + ";" + i.getProductFile());
                }

                write.close();
            } catch (FileNotFoundException fnff) {

                System.out.println("File not found");
            }
        }
    }


    public static ArrayList<Warehouse> readWarehouse() {

        String memAddress = FilePath.getMemAddress();

            // Creates an arraylist to store the warehouses
        ArrayList<Warehouse> warehouses = new ArrayList<>();

        try {

            // Scanner to read textfile
            Scanner read = new Scanner(new File(memAddress + "warehouselist.txt"));

            do {

                 // Read a line in the textfile, split it by semi-colons, and create a new warehouse object
                String line = read.nextLine();
                String[] tokens = line.split(";");
                Warehouse warehouseObj = new Warehouse(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], tokens[6]);

                // Add the object to the arraylist
                warehouses.add(warehouseObj);

            } while (read.hasNext());

            // Error handling
        } catch (FileNotFoundException fnf) {

            try {

                Scanner read = new Scanner(new File("/Users/pengu/OneDrive/Documents/School/CECS 343/Salesman Software/src/mem/warehouselist.txt"));

                do {

                    // Read a line in the textfile, split it by semi-colons, and create a new warehouse object
                    String line = read.nextLine();
                    String[] tokens = line.split(";");
                    Warehouse warehouseObj = new Warehouse(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], tokens[6]);
        
                    // Add the object to the arraylist
                    warehouses.add(warehouseObj);
        
                } while (read.hasNext());
            } catch (FileNotFoundException fnfm) {

                System.out.println("File not found Mac version");
            }
        }
        
        return warehouses;
    }

    /**
     * Reads in file of salespeople
     * @return  arraylist storing all salespeople
     */
    public static ArrayList<Salesperson> readSalesperson() {

        String memAddress = FilePath.getMemAddress();

        ArrayList<Salesperson> salespeople = new ArrayList<>();

        try {

            Scanner read = new Scanner(new File(memAddress + "salespersonlist.txt"));

            do {

                String line = read.nextLine();

                String[] tokens = line.split(";");

                double rate = Double.parseDouble(tokens[3]);

                int sales = Integer.parseInt(tokens[4]);

                Salesperson employee = new Salesperson(tokens[0], tokens[1], tokens[2], rate, sales);

                salespeople.add(employee);

            } while (read.hasNext());

            read.close();
        } catch (FileNotFoundException fnf) {

            try {

                Scanner read = new Scanner(new File("/Users/pengu/OneDrive/Documents/School/CECS 343/Salesman Software/src/mem/salespersonlist.txt"));

                do {

                    String line = read.nextLine();

                    String[] tokens = line.split(";");

                    double rate = Double.parseDouble(tokens[3]);

                    int sales = Integer.parseInt(tokens[4]);

                    Salesperson employee = new Salesperson(tokens[0], tokens[1], tokens[2], rate, sales);

                    salespeople.add(employee);
                } while (read.hasNext());

                read.close();
            } catch (FileNotFoundException fnfm) {

                System.out.println("File not found Mac version");
            }
        }

        return salespeople;
    }

    public static void writeSalesperson(ArrayList<Salesperson> employee) {

        String memAddress = FilePath.getMemAddress();

        try {

            PrintWriter write = new PrintWriter(memAddress + "salespersonlist.txt");

            for (Salesperson i : employee) {

                write.println(i.getName() + ";" + i.getEmail() + ";" + i.getPhone() + ";" + i.getcRate() + ";" + i.getSales());
            }

            write.close();

        } catch (FileNotFoundException fnf) {

            try {

                PrintWriter write = new PrintWriter("/Users/pengu/OneDrive/Documents/School/CECS 343/Salesman Software/src/mem/salespersonlist.txt");

                for (Salesperson i : employee) {

                    write.println(i.getName() + ";" + i.getEmail() + ";" + i.getPhone() + ";" + i.getcRate() + ";" + i.getSales());
                }

                write.close();
            } catch (FileNotFoundException fnff) {

                System.out.println("File not found");
            }
        }
    }

    /**
     * Reads in file of closed invoices
     * @return  arraylist storing all closed invoices
     */
    public static ArrayList<Invoice> readClosed() {

        String memAddress = FilePath.getMemAddress();

        ArrayList<Invoice> closedinvoices = new ArrayList<>();

        try {

            Scanner read = new Scanner(new File(memAddress + "closedinvoices.txt"));

            do {

                String line = read.nextLine();

                String[] tokens = line.split(";");

                double price = Double.parseDouble(tokens[2]);

                Invoice closed = new Invoice(tokens[0], tokens[1], price, tokens[3], tokens[4]);
                closedinvoices.add(closed);

            } while (read.hasNext());

            read.close();

        } catch (FileNotFoundException fnf) {

            try {

                Scanner read = new Scanner(new File("/Users/pengu/OneDrive/Documents/School/CECS 343/Salesman Software/src/mem/closedinvoices.txt"));

                do {

                    String line = read.nextLine();

                    String[] tokens = line.split(";");
    
                    double price = Double.parseDouble(tokens[2]);
    
                    Invoice closed = new Invoice(tokens[0], tokens[1], price, tokens[3], tokens[4]);
                    closedinvoices.add(closed);

                } while (read.hasNext());

                read.close();
            } catch (FileNotFoundException fnfm) {

                System.out.println("File not found Mac version");
            }
        }

        return closedinvoices;
    }

    public static void writeClosedInvoice(ArrayList<Invoice> closed)
    {
        String memAddress = FilePath.getMemAddress();
        try {

            PrintWriter write = new PrintWriter(memAddress + "closedinvoices.txt");

            for (Invoice i : closed) {

                write.println(i.getCustomerName() + ";" + i.getCustomerAddress() +";" + i.getPrice() + ";" + i.getProduct() + ";" + i.getDeliveryOption() );
            }

            write.close();

        } catch (FileNotFoundException fnf) {

            try {

                PrintWriter write = new PrintWriter("/Users/pengu/OneDrive/Documents/School/CECS 343/Salesman Software/src/mem/closedinvoices.txt");

                for (Invoice i : closed) {

                    write.println(i.getCustomerName() + ";" + i.getCustomerAddress() +";" + i.getPrice() + ";" + i.getProduct() + ";" + i.getDeliveryOption() );
                }

                write.close();
            } catch (FileNotFoundException fnff) {

                System.out.println("File not found");
            }
        }

    }

    public static ArrayList<Invoice> readOpen()
    {
        String memAddress = FilePath.getMemAddress();
        ArrayList<Invoice> invoices = new ArrayList<>();
        try {

            Scanner read = new Scanner(new File(memAddress + "openInvoices.txt"));

            do {

                String line = read.nextLine();

                String[] tokens = line.split(";");

                double price = Double.parseDouble(tokens[2]);

                Invoice open = new Invoice(tokens[0], tokens[1], price, tokens[3], tokens[4]);
                invoices.add(open);
                

            } while (read.hasNext());

            read.close();
        } catch (FileNotFoundException fnf)
        {
            try {

                Scanner read = new Scanner(new File("/Users/pengu/OneDrive/Documents/School/CECS 343/Salesman Software/src/mem/openInvoices.txt"));

                do {

                    String line = read.nextLine();

                    String[] tokens = line.split(";");

                    double price = Double.parseDouble(tokens[2]);

                    Invoice open = new Invoice(tokens[0], tokens[1], price, tokens[3], tokens[4]);
                    invoices.add(open);
                } while (read.hasNext());

                read.close();
            } catch (FileNotFoundException fnfm) {

                System.out.println("File not found Mac version");
            }
        }
        return invoices;
    }

    public static void writeOpenInvoices(ArrayList<Invoice> open) {

        String memAddress = FilePath.getMemAddress();

        try {

            PrintWriter write = new PrintWriter(memAddress + "openInvoices.txt");

            for (Invoice i : open) {

                write.println(i.getCustomerName() + ";" + i.getCustomerAddress() +";" + i.getPrice() + ";" + i.getProduct() + ";" + i.getDeliveryOption() );
            }

            write.close();

        } catch (FileNotFoundException fnf) {

            try {

                PrintWriter write = new PrintWriter("/Users/pengu/OneDrive/Documents/School/CECS 343/Salesman Software/src/mem/openInvoices.txt");

                for (Invoice i : open) {

                    write.println(i.getCustomerName() + ";" + i.getCustomerAddress() +";" + i.getPrice() + ";" + i.getProduct() + ";" + i.getDeliveryOption() );
                }

                write.close();
            } catch (FileNotFoundException fnff) {

                System.out.println("File not found");
            }
        }
    }

    public static ArrayList<Customer> readCustomer() {

        String memAddress = FilePath.getMemAddress();

        ArrayList<Customer> customers = new ArrayList<>();

        try {

            Scanner read = new Scanner(new File(memAddress + "customers.txt"));

            do {

                String line = read.nextLine();

                String[] tokens = line.split(";");

                customers.add(new Customer(tokens[0], tokens[1], Double.parseDouble(tokens[2])));
            } while (read.hasNext());
        } catch (FileNotFoundException fnf) {

            try {

                Scanner read = new Scanner(new File("/Users/pengu/OneDrive/Documents/School/CECS 343/Salesman Software/src/mem/customers.txt"));

                do {

                    String line = read.nextLine();

                    String[] tokens = line.split(";");

                    customers.add(new Customer(tokens[0], tokens[1], Double.parseDouble(tokens[2])));
                } while (read.hasNext());
            } catch (FileNotFoundException fnfm) {

                System.out.println("File not found");
            }
        }

        return customers;
    }

    public static void writeCustomer(ArrayList<Customer> customers) {

        String memAddress = FilePath.getMemAddress();

        try {

            PrintWriter write = new PrintWriter(memAddress + "customers.txt");

            for (Customer i : customers) {

                write.println(i.getName() + ";" + i.getAddress() +";" + i.getTax());
            }

            write.close();

        } catch (FileNotFoundException fnf) {

            try {

                PrintWriter write = new PrintWriter("/Users/pengu/OneDrive/Documents/School/CECS 343/Salesman Software/src/mem/customers.txt");

                for (Customer i : customers) {

                    write.println(i.getName() + ";" + i.getAddress() +";" + i.getTax());
                }
    
                write.close();
            } catch (FileNotFoundException fnff) {

                System.out.println("File not found");
            }
        }
    }

    public static ArrayList<Product> getAllProducts(ArrayList<Warehouse> warehouses) {

        ArrayList<Product> allProduct = new ArrayList<>();

        for (Warehouse i : warehouses) {

            ArrayList<Product> setList = i.getProductList();

            for (Product j : setList) {

                allProduct.add(j);
            }
        }

        return allProduct;
    }
}