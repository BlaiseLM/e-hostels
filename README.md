# eHotels

A hotel booking and management application for five hotel chains across North America. Customers can search room availability and make bookings. Employees can manage rooms, handle check-ins, and create rentings directly.

## Features

- Room search by price, capacity, view type, extendability, and date range
- Customer booking and check-in flow
- Employee can convert a booking to a renting, or create a renting without a prior booking
- Employee, customer, hotel, and room management
- Archive history for bookings and rentings

## Stack

- **Database:** PostgreSQL
- **Database Hosting:** Supabase
- **API:** Spring Boot with raw JDBC (JdbcTemplate)
- **Frontend:** HTML, JavaScript
- **API Testing:** Postman

## Getting Started

### Prerequisites

- Java 21
- Maven

### Database Setup

You will receive a Supabase project invite. Once accepted:

1. Go to the project dashboard
2. Click **Connect** at the top
3. Select **Session Pooler**. You must use this, not Direct Connection
4. Note down the host, project ID, and password

### Configuration

Create `application.properties` inside `src/main/resources/` and fill in your values:

```application.properties.example
spring.application.name=app

spring.datasource.url=jdbc:postgresql://<HOST>:5432/postgres?currentSchema=Hotel
spring.datasource.username=postgres.<project_id>
spring.datasource.password=<password>
spring.datasource.hikari.schema=Hotel

spring.datasource.driver-class-name=org.postgresql.Driver
logging.level.org.springframework.jdbc.core=DEBUG
```

### Running the Application

From the project root (same level as `pom.xml`):

```bash
JAVA_TOOL_OPTIONS="-Djava.net.preferIPv4Stack=true" mvn spring-boot:run
```

**You must use the IPv4 flag above.** The app will not connect to Supabase without it. Make sure port 8080 is free before running.

The API will be available at `http://localhost:8080`.

### API Testing

- **Frontend:** Open the frontend in your browser once the server is running
- **Postman:** Import the provided Postman collection
- **curl:** Send requests directly from the terminal

## Contributors: 
- Blaise Mahamoodally (300436389)
- Minsuk Aiden Kang (300433177)
- Dat Thanh Le (6056821)