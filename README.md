# Spring Boot Observability

Demo Spring Boot application showcasing integration with the Grafana observability stack.

## Features
- **Spring Boot** application with REST API to manage items.
- **Observability** using Prometheus, Grafana, Tempo, and Loki.
- **Dockerized** setup with `Dockerfile` and `docker-compose.yml`.
- **H2 Database** for in-memory data storage.
- **Tracing and Logging** with Zipkin, Loki, and Micrometer.

---

## Prerequisites
- Docker and Docker Compose installed on your system.
- Java 17 or higher installed (for local development).

---

## Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com/raju4789/spring-boot-observability.git
cd spring-boot-observability
```

### 2. Build the Application
To build the application locally:
```bash
./mvnw clean package -DskipTests
```

### 3. Run with Docker Compose
To start the application and observability stack:
```bash
docker-compose up --build
```

This will start the following services:
- **Spring Boot Application**: `http://localhost:8080`
- **Prometheus**: `http://localhost:9090`
- **Grafana**: `http://localhost:3000` (Default credentials: `admin/password`)
- **Tempo**: `http://localhost:3200`
- **Loki**: `http://localhost:3100`

---

## API Endpoints
### Items API
- **GET /api/items**: Fetch all items.

Example Response:
```json
[
  { "id": 1, "name": "Item 1" },
  { "id": 2, "name": "Item 2" },
  { "id": 3, "name": "Item 3" }
]
```

---

## Observability Stack

### 1. **Prometheus**
- Collects metrics from the Spring Boot application.
- Metrics endpoint: `/actuator/prometheus`.

### 2. **Grafana**
- Visualizes metrics and traces.
- Pre-configured data sources:
  - **Prometheus**: For metrics.
  - **Tempo**: For distributed tracing.
  - **Loki**: For logs.

### 3. **Tempo**
- Collects distributed traces.
- Integrated with Zipkin tracing.

### 4. **Loki**
- Collects and visualizes logs.
- Configured with `logback-spring.xml`.

---

## Configuration

### Application Configuration
The application is configured using `application.yml`:
```yaml
spring:
  application:
    name: spring-boot-observability
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
    username: sa
    password:
  jpa:
    hibernate:
      ddl-auto: update
  h2:
    console:
      enabled: true
      path: /h2-console
```

### Prometheus Configuration
Prometheus is configured in `docker/prometheus/prometheus.yml`:
```yaml
scrape_configs:
  - job_name: 'spring-boot-observability'
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets: ['spring-boot-observability:8080']
```

### Grafana Configuration
Grafana data sources are pre-configured in `docker/grafana/datasource.yml`.

---

## Development

### Run Locally
To run the application locally:
```bash
./mvnw spring-boot:run
```

Access the application at `http://localhost:8080`.

### H2 Console
Access the H2 database console at `http://localhost:8080/h2-console`.

---

## Docker Setup

### Dockerfile
The `Dockerfile` builds the Spring Boot application:
```dockerfile
FROM maven:3.8.5-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

### Docker Compose
The `docker-compose.yml` file sets up the entire observability stack:
- Spring Boot application
- Prometheus
- Grafana
- Tempo
- Loki

---

## License
This project is licensed under the Apache License 2.0. See the `LICENSE` file for details.
