# DevTask

A Spring Boot REST API for managing personal tasks, secured with JWT-based authentication. Each user registers/logs in and manages their own set of tasks, used many to one relation and postgreSQL for database

## Tech Stack

- **Java 17**
- **Spring Boot 3.3.4**
  - Spring Web
  - Spring Data JPA
  - Spring Security
- **PostgreSQL** — database
- **JWT (jjwt 0.12.5)** — stateless authentication
- **Maven** — build tool

## Features

- User registration and login
- JWT-based authentication for protected endpoints
- Full CRUD on tasks (title, description, status, due date)
- Each task is linked to the user who created it

## Project Structure

```
src/main/java/com/mitarth/devtask
├── controller/     # REST controllers (Task, User)
├── dto/            # Request/response DTOs
├── model/          # JPA entities (Task, Users)
├── repo/           # Spring Data repositories
├── securityConfig/ # JWT filter + Spring Security config
└── service/        # Business logic (Task, User, JWT)
```

## Getting Started

### Prerequisites

- JDK 17+
- Maven (or use the included `mvnw` wrapper)
- PostgreSQL running locally

### Setup

1. Clone the repo:
   ```bash
   git clone https://github.com/mitarthpathak/DevTask.git
   cd DevTask
   ```

2. Create a PostgreSQL database and update `src/main/resources/application.properties` with your DB URL, username, and password:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/your_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

3. Run the app:
   ```bash
   ./mvnw spring-boot:run
   ```

   The app starts on `http://localhost:8080` by default.

## API Endpoints

### Auth

| Method | Endpoint    | Description                          |
|--------|-------------|---------------------------------------|
| GET    | `/welcome`  | Sample landing page response          |
| POST   | `/register` | Register a new user                   |
| POST   | `/login`    | Log in and receive a JWT token        |

### Tasks

| Method | Endpoint      | Description             |
|--------|---------------|--------------------------|
| GET    | `/tasks`      | Get all tasks            |
| GET    | `/tasks/{id}` | Get a task by ID         |
| POST   | `/tasks`      | Create a new task        |
| POST   | `/tasks/{id}` | Update an existing task  |
| DELETE | `/tasks/{id}` | Delete a task            |

**Task fields:** `title`, `description`, `status`, `dueDate`

## Author

Built by [Mitarth Pathak](https://github.com/mitarthpathak).
