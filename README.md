# ecommerce-backend-springboot

A RESTful ecommerce backend built with Spring Boot, secured with JWT authentication and role-based access control (USER/ADMIN). Covers product management, category management, order lifecycle, and user registration.

---

## Tech Stack

- **Java 21**
- **Spring Boot 4.0.6**
- **Spring Security** (JWT-based stateless authentication)
- **Spring Data JPA** (Hibernate)
- **MySQL**
- **JJWT 0.11.5**
- **SpringDoc OpenAPI** (Swagger UI)
- **Bean Validation** (Jakarta)

---

## Features

- User registration and login with JWT token generation
- Role-based access control — USER and ADMIN roles
- Product CRUD — add, update, delete, get by ID, get by name
- Category management — add categories, get products by category
- Order management — place order, view orders, cancel order, update order status
- Automatic stock deduction on order placement and restock on cancellation
- Global exception handling with structured error responses
- DTO pattern for all request and response payloads
- Input validation on all request bodies

---

## Project Structure

```
src/
├── controller/         # REST controllers (Product, Order, Category)
├── Security/           # JWT filter, AuthController, SecurityConfig
├── service/            # Business logic
├── entity/             # JPA entities (User, Product, Order, OrderItem, Category)
├── repository/         # Spring Data JPA repositories
├── dto/
│   ├── RequestDTOs/    # Incoming request bodies
│   └── ResponseDTOs/   # Outgoing response payloads
└── Exception/          # Global exception handler, custom exceptions
```

---

## API Endpoints

### Auth
| Method | Endpoint              | Access | Description          |
|--------|-----------------------|--------|----------------------|
| POST   | /auth/register        | Public | Register as USER     |
| POST   | /auth/register/Admin  | Public | Register as ADMIN    |
| POST   | /auth/login           | Public | Login and get JWT    |

### Products
| Method | Endpoint              | Access | Description             |
|--------|-----------------------|--------|-------------------------|
| GET    | /products             | Public | Get all products        |
| GET    | /products/{id}        | Public | Get product by ID       |
| GET    | /products/{name}/name | Public | Search products by name |
| POST   | /products             | ADMIN  | Add a product           |
| PUT    | /products/{id}        | ADMIN  | Update a product        |
| DELETE | /products/{id}        | ADMIN  | Delete a product        |

### Categories
| Method | Endpoint              | Access | Description                   |
|--------|-----------------------|--------|-------------------------------|
| GET    | /categories           | Public | Get all categories            |
| GET    | /categories/{id}      | Public | Get products by category      |
| POST   | /categories           | ADMIN  | Add a category                |

### Orders
| Method | Endpoint              | Access | Description              |
|--------|-----------------------|--------|--------------------------|
| POST   | /orders               | USER   | Place an order           |
| GET    | /orders/{id}          | USER   | Get orders by user ID    |
| PUT    | /orders/{id}/cancel   | USER   | Cancel an order          |
| PUT    | /orders/{id}          | ADMIN  | Update order status      |

---

## Getting Started

### Prerequisites

- Java 21
- MySQL
- Maven

### Setup

1. Clone the repository
   ```bash
   git clone https://github.com/sabari5h/ecommerce-backend-springboot.git
   cd ecommerce-backend-springboot
   ```

2. Create a `.env` file in the project root based on `.env.example`
   ```
   DB_USERNAME=your_mysql_username
   DB_PASSWORD=your_mysql_password
   JWT_SECRET=your_jwt_secret_key
   ```

3. Run the application
   ```bash
   ./mvnw spring-boot:run
   ```

4. The API will be available at `http://localhost:8080`

5. Swagger UI available at `http://localhost:8080/swagger-ui/index.html`

---

## Authentication

All protected endpoints require a Bearer token in the Authorization header.

1. Register a user via `POST /auth/register`
2. Login via `POST /auth/login` to receive a JWT token
3. Include the token in subsequent requests:
   ```
   Authorization: Bearer <your_token>
   ```

---

## Environment Variables

| Variable     | Description              |
|--------------|--------------------------|
| DB_USERNAME  | MySQL database username  |
| DB_PASSWORD  | MySQL database password  |
| JWT_SECRET   | Secret key for JWT       |

---

## License

This project is licensed under the MIT License.
