# Fx Deal Cluster

This documentation provides an overview of the Spring Boot application along with instructions for Docker deployment.

## Application Overview
The Spring Boot application is a RESTful service designed to accept FX deals details and persist them into a database. It includes validation for deal structures and prevents importing duplicate requests. The application is built using Spring Boot, and it uses MySQL as the database.

## Docker Deployment
To deploy the Spring Boot application with Docker, follow these steps:

1. **Build Docker Image**: Run the following command to build the Docker image for the Spring Boot application:
    ```bash
    docker build -t spring-boot-app .
    ```

2. **Run Docker Compose**: Run the following command to start the Docker containers using Docker Compose:
    ```bash
    docker-compose up
    ```

3. **Access Application**: Once the containers are running, you can access the Spring Boot application at `http://localhost:8080`.
### Configuration
The application configuration can be found in the `application.properties` file. Modify this file to change the application properties such as database connection details.

### Testing
The application includes unit tests for both the controller and service layers. To run the tests, execute the following command:
```bash
mvn test

### Dependencies
The application utilizes the following dependencies:

Spring Boot Starter Web
Spring Boot Starter Data JPA
Spring Boot Starter Test
MySQL Driver

### Sample Request and Response

Sample Successful Request:

{
  "uniqueId": "ABC123",
  "fromCurrencyIsoCode": "USD",
  "toCurrencyIsoCode": "EUR",
  "dealTimestamp": "2024-05-10T12:30:00",
  "dealAmount": 1000.00
}
Sample Successful Response:
{
  "status": 0,
  "error": null,
  "message": "Successful",
  "data": {
    "id": 1,
    "uniqueId": "ABC123",
    "fromCurrencyIsoCode": "USD",
    "toCurrencyIsoCode": "EUR",
    "dealTimestamp": "2024-05-10T12:30:00",
    "dealAmount": 1000.00
  }
}

Sample Failure Response:

{
  "status": 409,
  "error": "Bad Request",
  "message": "Deal with the same unique ID already exists: ABC123",
  "data": null
}


#### Swagger Documentation
Swagger documentation is provided for the Spring Boot application. You can access the Swagger UI at http://localhost:8080/swagger-ui.html when the application is running.
