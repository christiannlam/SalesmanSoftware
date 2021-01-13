CREATE TABLE warehouse (
	warehouseName VARCHAR(50) NOT NULL,
    address VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    state VARCHAR(50) NOT NULL,
    zip VARCHAR(50) NOT NULL,
    phoneNumber VARCHAR(50) NOT NULL,
    warehouseID VARCHAR(50) NOT NULL,
    CONSTRAINT warehouse_pk PRIMARY KEY (warehouseID)
);

CREATE TABLE product (
	productName VARCHAR(50) NOT NULL,
    quantity INT NOT NULL,
    price DOUBLE NOT NULL,
    model VARCHAR(50) NOT NULL,
    warehouseID VARCHAR(50) NOT NULL,
    CONSTRAINT product_pk PRIMARY KEY (productName, price, model),
    CONSTRAINT product_fk FOREIGN KEY (warehouseID) REFERENCES warehouse(warehouseID)
);

CREATE TABLE salesperson (
	salespersonName VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    phone VARCHAR(50) NOT NULL,
    commissionRate DOUBLE NOT NULL,
    sales INT NOT NULL,
    employeeID VARCHAR(50) NOT NULL,
    CONSTRAINT salesperson_pk PRIMARY KEY (salespersonName, employeeID)
);

CREATE TABLE invoice (
	customerName VARCHAR(50) NOT NULL,
    customerAddress VARCHAR(50) NOT NULL,
    productName VARCHAR(50) NOT NULL,
    price DOUBLE NOT NULL,
    model VARCHAR(50) NOT NULL,
    deliveryOption VARCHAR(50) NOT NULL,
    salespersonName VARCHAR(50) NOT NULL,
    employeeID VARCHAR(50) NOT NULL,
    CONSTRAINT invoice_pk PRIMARY KEY (customerName, customerAddress),
    CONSTRAINT invoice_fk FOREIGN KEY (productName, price, model) REFERENCES product (productName, price, model),
    CONSTRAINT invoice_fk2 FOREIGN KEY (salespersonName, employeeID) REFERENCES salesperson (salespersonName, employeeID)
);