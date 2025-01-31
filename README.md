# Spring Boot Observability

Demo Spring Boot application showcasing integration with the Grafana observability stack.

## Features
- **Spring Boot** application with dummy REST API to send some logs and traces.
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

### 2. Run with Docker Compose
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

## API Endpoints(Just created to send some metrics, logs and traces for testing)
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
Grafana data sources are pre-configured in `docker/grafana/provisioning/datasources/datasource.yml`.

<img width="1727" alt="Screenshot 2568-01-31 at 23 01 01" src="https://github.com/user-attachments/assets/73a98927-a612-48ad-ad04-c24910de9ea2" />


---

## Results
### Loki
<img width="1728" alt="Screenshot 2568-01-31 at 21 37 37" src="https://github.com/user-attachments/assets/cf069982-bf9c-4777-9fe5-58649301d718" />

### Tempo

<img width="1727" alt="Screenshot 2568-01-31 at 21 39 58" src="https://github.com/user-attachments/assets/8a2590a4-bb45-457c-a1a3-fceeca8210d3" />

### Prometheus

<img width="1722" alt="Screenshot 2568-01-31 at 21 41 50" src="https://github.com/user-attachments/assets/2d1244ae-38a4-4808-a29e-20b02edf1a74" />

### Docker Compose
The `docker-compose.yml` file sets up the entire observability stack:
- Spring Boot application
- Prometheus
- Grafana
- Tempo
- Loki

---
