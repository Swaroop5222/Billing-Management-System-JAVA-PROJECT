CREATE DATABASE IF NOT EXISTS BillingSystem;

USE BillingSystem;

CREATE TABLE IF NOT EXISTS Login
(
    User_name VARCHAR(50),
    User_ID VARCHAR(50),
    pass_key VARCHAR(50),
    User_Role VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS Products
(
    Product_ID VARCHAR(10),
    Product_Name VARCHAR(15),
    Price INT(10),
    P_Description VARCHAR(50),
    Activate VARCHAR(5),
    Discount INT(10)
);

CREATE TABLE Billing(
	Customer_Name VARCHAR(20),
	Contact_No VARCHAR(10),
	Product_ID VARCHAR(10),
    Product_Name VARCHAR(15),
    Price INT,
    Quantity INT,
    Total INT,
    P_Time VARCHAR(50)
);

CREATE TABLE Payment(
	Customer_Name VARCHAR(20),
    Contact_No VARCHAR(10) ,
	Total VARCHAR(20) ,
    P_Time VARCHAR(50)
);

-- INSERT INTO Login (User_name, User_ID, pass_key) VALUES ("TEJA", "1234", "123");
INSERT INTO Login (User_name, User_ID, pass_key,User_Role) VALUES ("Swaroop", "12345", "1234","Manager");
INSERT INTO Login (User_name, User_ID, pass_key,User_Role) VALUES ("Santhosh", "0123", "123","Cashier");



