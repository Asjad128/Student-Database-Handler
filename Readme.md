# Course Management System

A robust and scalable RESTful API for managing courses, students, and departments. Built with Spring Boot 4.1.0 and MySQL, this system provides comprehensive course management capabilities with proper validation and error handling.

## Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Project Structure](#project-structure)
- [Database Schema](#database-schema)

## Features

✨ **Core Features:**
- Create, read, update, and delete courses
- Manage student information and enrollments
- Organize courses by departments
- Address management for students
- Many-to-many relationships between courses and students
- Global exception handling with meaningful error messages
- Request validation using Spring Validation
- H2 Console support for development/testing

## Tech Stack

- **Framework:** Spring Boot 4.1.0
- **Language:** Java 21
- **Database:** MySQL 8.0+
- **ORM:** Spring Data JPA / Hibernate
- **Build Tool:** Maven
- **Additional Libraries:**
  - Spring Web MVC
  - Spring Data JPA
  - Spring Validation
  - H2 Console (for development)

## Prerequisites

Before you begin, ensure you have the following installed:

- **Java 21** or higher
- **Maven 3.6+**
- **MySQL 8.0+**
- **Git** (optional)

## Installation

### 1. Clone the Repository

```bash
git clone <repository-url>
cd coursemanagement
```

### 2. Create Database

Create a MySQL database for the application:

```sql
CREATE DATABASE coursemanagement;
```

### 3. Configure Database Connection

Update the database credentials in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/coursemanagement
spring.datasource.username=your_mysql_user
spring.datasource.password=your_mysql_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

### 4. Build the Project

```bash
mvn clean install
```

## Configuration

### Application Properties

The `application.properties` file contains key configuration settings:

```properties
# Application Name
spring.application.name=coursemanagement

# MySQL Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/coursemanagement
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
```

**Property Descriptions:**
- `ddl-auto=update`: Automatically updates schema based on entity definitions
- `show-sql=true`: Logs SQL queries (disable in production)
- `format_sql=true`: Formats logged SQL for better readability

## Running the Application

### Using Maven

```bash
mvn spring-boot:run
```

### Using Java directly

```bash
mvn clean package
java -jar target/coursemanagement-0.0.1-SNAPSHOT.jar
```

The application will start on `http://localhost:8080`

### H2 Console (Development)

Access the H2 console at: `http://localhost:8080/h2-console`

## API Endpoints

### Courses

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/courses` | Create a new course |
| GET | `/courses` | Get all courses |
| GET | `/courses/{id}` | Get course by ID |
| PUT | `/courses/{id}` | Update a course |
| DELETE | `/courses/{id}` | Delete a course |

### Students

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/students` | Create a new student |
| GET | `/students` | Get all students |
| GET | `/students/{id}` | Get student by ID |
| PUT | `/students/{id}` | Update a student |
| DELETE | `/students/{id}` | Delete a student |

### Departments

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/departments` | Create a new department |
| GET | `/departments` | Get all departments |
| GET | `/departments/{id}` | Get department by ID |
| PUT | `/departments/{id}` | Update a department |
| DELETE | `/departments/{id}` | Delete a department |

### Example Requests

**Create a Course:**
```bash
curl -X POST http://localhost:8080/courses \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Java Programming",
    "duration": "4 weeks"
  }'
```

**Get All Courses:**
```bash
curl http://localhost:8080/courses
```

## Project Structure

```
coursemanagement/
├── src/
│   ├── main/
│   │   ├── java/com/demo/coursemanagement/
│   │   │   ├── controller/          # REST API endpoints
│   │   │   │   ├── CourseController.java
│   │   │   │   ├── StudentController.java
│   │   │   │   └── DepartmentController.java
│   │   │   ├── service/             # Business logic layer
│   │   │   │   ├── CourseService.java
│   │   │   │   ├── StudentService.java
│   │   │   │   └── DepartmentService.java
│   │   │   ├── repository/          # Data access layer
│   │   │   │   ├── CourseRepository.java
│   │   │   │   ├── StudentRepository.java
│   │   │   │   ├── DepartmentRepository.java
│   │   │   │   └── AddressRepository.java
│   │   │   ├── entity/              # JPA Entities
│   │   │   │   ├── Course.java
│   │   │   │   ├── Student.java
│   │   │   │   ├── Department.java
│   │   │   │   └── Address.java
│   │   │   ├── dto/                 # Data Transfer Objects
│   │   │   ├── exception/           # Custom exceptions & handlers
│   │   │   │   ├── ResourceNotFoundException.java
│   │   │   │   └── GlobalExceptionHandler.java
│   │   │   ├── config/              # Configuration classes
│   │   │   └── CoursemanagementApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       └── templates/
│   └── test/                        # Unit tests
├── pom.xml                          # Maven configuration
├── mvnw & mvnw.cmd                  # Maven wrapper scripts
└── Readme.md                        # This file
```

## Database Schema

### Entities & Relationships

**Course**
- id (PK)
- name
- duration
- Relationships: Many-to-Many with Student

**Student**
- id (PK)
- firstName
- lastName
- email
- courses (Many-to-Many)
- address (One-to-One)

**Department**
- id (PK)
- name
- description

**Address**
- id (PK)
- street
- city
- state
- zipCode

### Relationships

```
Department (1) ──────── (Many) Student
Student (Many) ──────── (Many) Course
Student (1) ──────---- (1) Address
```

## Error Handling

The application implements global exception handling through `GlobalExceptionHandler`:

- **ResourceNotFoundException:** Thrown when a requested resource is not found (HTTP 404)
- **Validation Errors:** Handled automatically by Spring Validation (HTTP 400)
- **Other Exceptions:** Caught and logged with meaningful error responses

## Development Notes

- The application uses **H2 in-memory database** option for quick testing
- Ensure MySQL is running before switching from H2 to MySQL
- All entities use JPA annotations for ORM mapping
- Services follow the Service layer pattern for business logic separation
- Controllers use `ResponseEntity` for flexible HTTP response handling

## Future Enhancements

- [ ] Implement pagination for list endpoints
- [ ] Add filtering and search capabilities
- [ ] Implement JWT authentication
- [ ] Add API documentation with Swagger/OpenAPI
- [ ] Add comprehensive unit and integration tests
- [ ] Implement logging with SLF4J/Logback
- [ ] Add caching mechanisms

## License

This project is open source and available under the MIT License.

## Support

For issues, questions, or suggestions, please create an issue in the repository or contact the development team.

---

**Last Updated:** 2026-08-24  
**Version:** 0.0.1-SNAPSHOT
