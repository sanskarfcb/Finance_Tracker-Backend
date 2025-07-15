#  Finance Tracker API

A secure and feature-rich Finance Tracker backend built using **Spring Boot**, **PostgreSQL**, and **JWT authentication**. This RESTful API allows users to register, log in, and manage their income/expense transactions with advanced filtering, pagination, and analytics.

---

##  Features

-  **User Authentication** (JWT-based)
-  Add/Edit/Delete Transactions
-  Filter by Type, Category, Title
-  Income/Expense Summary
-  Pagination for Transaction List
-  Global Exception Handling
-  Input Validation (Spring Validator)
-  Password Hashing using BCrypt

---

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot 3**
- **Spring Security**
- **JWT (JSON Web Token)**
- **PostgreSQL**
- **Lombok**
- **MapStruct**
- **Maven**
- **Postman** (for testing)
- **Swagger** (optional for API docs)

  ## 📂 Project Structure
  src/
├── config/              # Security Config, CORS, JWT
├── controller/          # REST Controllers
├── dto/                 # Data Transfer Objects
├── entity/              # JPA Entities
├── exception/           # Global Exception Handling
├── repository/          # Spring Data JPA Repositories
├── service/             # Service Layer
├── util/                # Utility classes (e.g. JWT Provider)
└── FinanceApp.java      # Main class

---

## 📦 Setup Instructions

1. **Clone the Repo**
   ```bash
   git clone https://github.com/your-username/finance-tracker-api.git
   cd finance-tracker-api
   	2.	Configure PostgreSQL DB
	•	Create a database called finance_app
	•	Update your DB credentials in application.properties

Authentication Flow
	1.	Register (/auth/register)
	2.	Login (/auth/login) → returns a JWT
	3.	Use JWT in Authorization: Bearer <token> for all secured endpoints


Sanskar Mishra
📍 Varanasi, India
📧 sanskarmishra.work@gmail.com

