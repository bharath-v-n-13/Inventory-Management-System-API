# 📦 Inventory Management System API

A backend REST API built using **Spring Boot 3** and **MySQL** for managing warehouse products, stock, and inventory levels.  
It provides full CRUD functionality, stock increase/decrease operations, and low-stock monitoring.

---

## 🚀 Features

✅ Full CRUD operations for products  
✅ Business logic to prevent negative stock levels  
✅ Endpoints to **increase/decrease stock** safely  
✅ Low stock threshold alerts and filtering  
✅ Proper error handling and response messages  
✅ Unit tests for core inventory operations  

---

## 🗂️ Project Structure

```plaintext
inventory-api/
├── src/
│   ├── main/
│   │   ├── java/com/inventory/api/
│   │   │   ├── controller/          # REST Controllers
│   │   │   ├── model/               # Entity classes (Product)
│   │   │   ├── repository/          # JPA Repositories
│   │   │   ├── service/             # Business logic layer
│   │   │   └── InventoryApiApplication.java
│   │   └── resources/
│   │       ├── application.properties   # Database configurations
│   │       └── data.sql (optional sample data)
│   └── test/
│       └── java/com/inventory/api/      # Unit & integration tests
├── pom.xml
└── README.md
⚙️ Tech Stack
Java 21

Spring Boot 3.x

Spring Data JPA

MySQL

Maven

Postman (for API testing)

🏗️ Setup Instructions
1️⃣ Clone the repository
bash
Copy code
git clone https://github.com/YOUR-USERNAME/Inventory-Management-System-API.git
cd Inventory-Management-System-API/inventory-api
2️⃣ Configure MySQL Database
Open src/main/resources/application.properties and update credentials:

properties
Copy code
spring.datasource.url=jdbc:mysql://localhost:3306/inventory_db
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
3️⃣ Build and run the application
bash
Copy code
mvn clean package
mvn spring-boot:run
Application will start at:
👉 http://localhost:8080

🧪 API Endpoints
🔹 Product CRUD
Method	Endpoint	Description
POST	/api/products	Create a new product
GET	/api/products	Get all products
GET	/api/products/{id}	Get product by ID
PUT	/api/products/{id}	Update a product
DELETE	/api/products/{id}	Delete a product

🔹 Stock Operations
Method	Endpoint	Description
POST	/api/products/{id}/addStock	Increase stock quantity
POST	/api/products/{id}/removeStock	Decrease stock quantity (checks for sufficient stock)

Example Body:

json
Copy code
{
  "amount": 5
}
🔹 Low Stock Products
Method	Endpoint	Description
GET	/api/products/low-stock	Get products below their low stock threshold

📘 Example JSON for Creating a Product
json
Copy code
{
  "name": "iPhone 17 Pro Max",
  "description": "Latest Apple flagship phone",
  "stockQuantity": 10,
  "lowStockThreshold": 3
}
🧰 Running Tests
To execute all unit tests:

bash
Copy code
mvn test
This runs JUnit tests for:

Stock increase logic

Stock decrease logic (including edge cases)

Product CRUD operations

🧩 Postman Collection
A Postman collection is included for easy API testing:
📁 inventory-api-postman-collection.json

To use:

Open Postman → Import

Choose the .json collection file

Set {{baseUrl}} to http://localhost:8080

Test all API endpoints directly

💡 Design Choices & Assumptions
Stock cannot go below zero.

Low stock is determined by comparing stock_quantity with low_stock_threshold.

Errors (e.g., invalid product ID, insufficient stock) return a structured JSON response.

Business logic is isolated in the service layer for testability.
