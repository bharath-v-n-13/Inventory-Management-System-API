# 📦 Inventory Management System API

A backend REST API built with Spring Boot 3 and MySQL for managing warehouse products, stock, and inventory levels.  
Provides full CRUD functionality, safe stock increase/decrease operations, and low-stock monitoring.

---

## 🚀 Features

- ✅ Full CRUD operations for products
- ✅ Business logic to prevent negative stock levels
- ✅ Endpoints to increase/decrease stock safely
- ✅ Low stock threshold alerts and filtering
- ✅ Proper error handling and response messages
- ✅ Unit tests for core inventory operations

---

## 🗂️ Project Structure

```
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
```

---

## ⚙️ Tech Stack

- ☕ Java 21
- 🧩 Spring Boot 3.x
- 🗃️ Spring Data JPA
- 🐬 MySQL
- 🔧 Maven
- 🧪 Postman (for API testing)

---

## 🛠️ Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/bharath-v-n-13/Inventory-Management-System-API.git
cd Inventory-Management-System-API/inventory-api
```

### 2. Configure MySQL Database

In `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/inventory_db
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

> Replace `YOUR_PASSWORD` with your actual MySQL password.  
> Ensure that the database `inventory_db` exists (or update the name accordingly).

### 3. Build & Run

```bash
mvn clean package
mvn spring-boot:run
```

The application will start at:  
[http://localhost:8080](http://localhost:8080)

---

## 📡 API Endpoints

### 🔹 Product CRUD

| Method | Endpoint                | Description            |
|--------|------------------------|------------------------|
| POST   | `/api/products`        | Create a new product   |
| GET    | `/api/products`        | Get all products       |
| GET    | `/api/products/{id}`   | Get product by ID      |
| PUT    | `/api/products/{id}`   | Update a product       |
| DELETE | `/api/products/{id}`   | Delete a product       |

### 🔹 Stock Operations

- **Add Stock**

  ```
  POST /api/products/{id}/addStock
  Content-Type: application/json

  {
    "amount": 5
  }
  ```

- **Remove Stock**

  ```
  POST /api/products/{id}/removeStock
  Content-Type: application/json

  {
    "amount": 3
  }
  ```

  > 🧠 Automatically prevents stock from going negative.

- **Low Stock Products**

  ```
  GET /api/products/low-stock
  ```

  Returns products where `stockQuantity < lowStockThreshold`.

---

## 🧪 Running Tests

```bash
mvn test
```

Covers:
- Stock increase/decrease logic
- CRUD operations
- Validation for low stock and negative stock

---

## 🧰 Postman Collection

If you have a Postman collection file (e.g. `inventory-api-postman-collection.json`):

1. Open Postman → Import
2. Select the JSON file
3. Set variable `{{baseUrl}}` = `http://localhost:8080`
4. Run the API requests

---

## 🧱 Design Notes

- Prevents negative stock automatically.
- Service layer contains business logic; controllers stay clean.
- Consistent error handling with descriptive messages.
- Easy to extend with authentication, roles, or reporting.

---

## 👨‍💻 Author

**Bharath V N**  
GitHub: [bharath-v-n-13/Inventory-Management-System-API](https://github.com/bharath-v-n-13/Inventory-Management-System-API)

---

⭐ If you found this project useful, give it a star on GitHub! ⭐
