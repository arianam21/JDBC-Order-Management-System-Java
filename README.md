# JDBC Order Management System (Java & MySQL)

**A desktop application developed in Java (Swing) for managing clients, products, and orders, utilizing a MySQL database via JDBC and implementing a layered (BLL/DAO) architecture with data validation.**

---

## Key Features

### Layered Architecture & Data Access
* **Business Logic Layer (BLL):** Handles validation and complex operations for Clients, Products, and Orders.
* **Data Access Object (DAO):** (Assumed to exist alongside BLL classes) Manages all interactions with the MySQL database using JDBC.
* **Connection Management:** Uses a Singleton pattern (`ConnectionFactory`) to manage database connections, drivers, and credentials (`applicationdb`).

### Data Validation
All entities are validated before insertion or update using the Strategy pattern (`Validator<T>` interface):
* **Client Validation:** Checks for valid email format and a phone number length of exactly 10 characters.
* **Product Validation:** Ensures price and stock quantity are positive numbers.
* **Order Validation:** Ensures quantity is positive and the shipping address is not empty.

### Order Processing
* **Order Creation:** Allows users to select an existing client and product via combo boxes, specify a quantity, and provide a shipping address.
* **Stock Check:** Before placing an order, the system verifies that there is sufficient stock quantity for the requested product.
* **Stock Update:** Upon successful order placement, the product's stock quantity is reduced accordingly.
* **Bill Generation:** A new `Bill` record is automatically created for every successfully placed order, containing client, product, and price details.

### CRUD Operations (Client & Product)
* **Client Management:** Dedicated view for adding, editing (updating selected table row), and deleting clients. Deleting a client also deletes all associated orders.
* **Product Management:** Dedicated view for adding, editing, and deleting products. Deleting a product is prevented if there are existing orders referencing it.

---

## Project Structure

* **`Model`**: Contains entity classes: `Client`, `Product`, `Order`, and `Bill`.
* **`BusinessLogic`**: Contains the BLL classes (`ClientBLL`, `ProductBLL`, `OrderBLL`) and the validation logic (`validators` package).
* **`DataAccess`**: Contains the `DAO` classes (not fully provided, but inferred by BLL usage) and `ConnectionFactory`.
* **`Presentation`**: Contains the GUI Views (`MainView`, `ClientView`, `ProductView`, `OrderView`) and their respective Controllers.

## Prerequisites

* **Java JDK:** Required for compiling and running the application.
* **MySQL Database:** A running MySQL instance is required.
* **Database Setup:** A database named `applicationdb` must exist, configured with tables for `Client`, `Product`, `Order`, and `Bill`.
* **JDBC Driver:** The MySQL Connector/J driver must be included in the project's classpath.

## How to Run the Project

1.  **Configure Database:** Ensure the database URL, username, and password in `Connection.ConnectionFactory.java` match your local MySQL configuration.
2.  The main execution class is `Presentation.MainView`.
3.  Launch the application to start the main menu GUI.

***
