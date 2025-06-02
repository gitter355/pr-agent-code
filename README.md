# User Management Spring Boot Application

A RESTful CRUD application built with Spring Boot for managing users. The application uses an H2 embedded database and provides comprehensive user management functionality.

## Features

- Create, Read, Update, Delete (CRUD) operations for users
- User validation with proper error handling
- Search functionality (by name, email, or general search term)
- H2 embedded database with sample data
- RESTful API endpoints
- Global exception handling
- Input validation

## Technologies Used

- Java 17
- Spring Boot 3.2.0
- Spring Data JPA
- H2 Database
- Maven
- Bean Validation

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

### Running the Application

1. Clone the repository
2. Navigate to the project directory
3. Run the application:

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### Accessing the H2 Database Console

Visit `http://localhost:8080/h2-console` with the following settings:
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (leave empty)

## API Endpoints

### Base URL: `http://localhost:8080/api/users`

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/` | Get all users |
| GET | `/{id}` | Get user by ID |
| POST | `/` | Create a new user |
| PUT | `/{id}` | Update user by ID |
| DELETE | `/{id}` | Delete user by ID |
| GET | `/email/{email}` | Get user by email |
| GET | `/search?term={searchTerm}` | Search users by name or email |
| GET | `/firstname/{firstName}` | Get users by first name |
| GET | `/lastname/{lastName}` | Get users by last name |

### Sample API Requests

#### Create a User
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "phoneNumber": "+1234567890"
  }'
```

#### Get All Users
```bash
curl http://localhost:8080/api/users
```

#### Get User by ID
```bash
curl http://localhost:8080/api/users/1
```

#### Update User
```bash
curl -X PUT http://localhost:8080/api/users/1 \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Jane",
    "lastName": "Doe",
    "email": "jane.doe@example.com",
    "phoneNumber": "+1234567891"
  }'
```

#### Delete User
```bash
curl -X DELETE http://localhost:8080/api/users/1
```

#### Search Users
```bash
curl "http://localhost:8080/api/users/search?term=John"
```

## User Model

```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phoneNumber": "+1234567890",
  "createdAt": "2023-12-01T10:00:00",
  "updatedAt": "2023-12-01T10:00:00"
}
```

### Validation Rules

- `firstName`: Required, max 50 characters
- `lastName`: Required, max 50 characters
- `email`: Required, valid email format, unique, max 100 characters
- `phoneNumber`: Optional, max 15 characters

## Error Handling

The application includes comprehensive error handling:

- **404 Not Found**: When user doesn't exist
- **409 Conflict**: When email already exists
- **400 Bad Request**: When validation fails
- **500 Internal Server Error**: For unexpected errors

## Sample Data

The application comes with sample data that's automatically loaded on startup:
- John Doe (john.doe@email.com)
- Jane Smith (jane.smith@email.com)
- Bob Johnson (bob.johnson@email.com)
- Alice Brown (alice.brown@email.com)
- Charlie Davis (charlie.davis@email.com)

## Development

### Building the Application
```bash
mvn clean compile
```

### Running Tests
```bash
mvn test
```

### Creating JAR
```bash
mvn clean package
```

## Configuration

The application can be configured via `src/main/resources/application.properties`:

- Database settings
- Server port
- Logging levels
- H2 console settings