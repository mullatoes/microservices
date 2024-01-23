# Microservices Repository(Pension System Microservices)

This repository hosts a set of microservices designed for managing a pension system. The project is divided into three primary microservices, each responsible for specific aspects of the pension system.

## Microservices Overview

### Members Service

- Manages member-related information.
- Handles member registration, updating personal details, and retrieving member information.
- Communicates with the Contributions Service for contribution-related details.

### Contributions Service

- Manages contribution-related operations for members.
- Allows members to view their contribution history, make contributions, and track their pension savings.
- Communicates with the Members Service for member details and with the Benefits/Exits Service for exit-related calculations.

### Benefits/Exits Service

- Handles benefit and exit-related operations for members.
- Calculates pension benefits based on contributions and other relevant factors.
- Manages member exits from the pension system, including retirement, withdrawals, or other exit scenarios.

## Technologies Used

### Spring Boot

- Utilized for building lightweight and efficient microservices.
- Implements the Spring Cloud framework for microservices development.

### Docker

- Enables containerization of microservices for easy deployment and scalability.

### Spring Cloud Eureka

- Implements service discovery for dynamic and seamless communication between microservices.

### Spring Cloud Config

- Manages externalized configuration for microservices.

### Spring Cloud Gateway

- Serves as the API gateway for routing and load balancing.

### Spring Cloud Sleuth and Zipkin

- Provides distributed tracing and monitoring capabilities.

### Java Persistence API (JPA) with Hibernate

- Manages data persistence in a relational database.

## Microservices Interaction

### Members Service <-> Contributions Service

- Members Service communicates with Contributions Service to retrieve contribution-related information.

### Contributions Service <-> Benefits/Exits Service

- Contributions Service communicates with Benefits/Exits Service for exit-related calculations.

## Database Schema

### Members Database

- **Table**: members
- **Columns**: memberId, firstName, lastName, dateOfBirth, email, phoneNumber, etc.

### Contributions Database

- **Table**: contributions
- **Columns**: contributionId, memberId, amount, date, etc.

### Benefits/Exits Database

- **Table**: benefits_exits
- **Columns**: exitId, memberId, exitType, benefitAmount, date, etc.

## Endpoints

### Members Service

- `/members` (GET, POST, PUT, DELETE): Manages member information.
- `/members/{memberId}/contributions` (GET): Retrieves contributions for a specific member.

### Contributions Service

- `/contributions` (GET, POST): Manages contribution information.
- `/contributions/{memberId}` (GET): Retrieves contributions for a specific member.

### Benefits/Exits Service

- `/exits` (GET, POST): Manages exit-related information.
- `/exits/{memberId}` (GET): Retrieves exits for a specific member.

### Prerequisites

- Java Development Kit (JDK) 11 or later
- Maven
- Docker (optional, for containerization)

### Installation

1. Clone this repository:

   ```bash
   git clone https://github.com/your-username/microservices.git

Feel free to explore each microservice for detailed README files on how to set up, run, and interact with them. Happy coding!
