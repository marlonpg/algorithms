# Goals of this POC
TODO - Add Gatling to Victor Hugo repo
     - run Gatling tests and Jmeter
        - 10.000 scenario
        - 100.000 scenario in both

- ✅ unit tests
- ✅ performance tests / benchmarks
- ✅ proper documentation
- ✅ expose solution via REST API
- ✅ store results into a database (Redis with Docker)

# Bitonic Sequence REST API

This is a REST API built with **Clojure** that generates bitonic sequences and caches results in **Redis**.
The application is fully containerized using **Docker** and **Docker Compose** for easy deployment.

---

## 📂 Project Structure

```
src/
└── api/
    ├── main.clj            ; Application entry point - starts Jetty server
    ├── controller.clj      ; Defines routes and HTTP handlers
    ├── bitonic_service.clj ; Business logic for bitonic sequence generation
    └── db.clj              ; Redis database operations
test/
└── api/
    ├── bitonic_service_test.clj ; Unit tests for service
    └── db_test.clj              ; Unit tests for database operations
deps.edn                  ; Project dependencies
Dockerfile                ; Docker container configuration
docker-compose.yml        ; Multi-container orchestration
.jvmopts                  ; JVM options for compatibility
```

---

## ⚙️ Dependencies & Prerequisites

**System Requirements:**
* Docker and Docker Compose
* Java (JDK 11, 17, or 21 recommended - avoid Java 25 for Gatling)
* Clojure CLI tools
* SBT (for performance tests)

**Installation Commands:**
```bash
# Install via Chocolatey (Windows)
choco install docker-desktop
choco install openjdk17
choco install clojure
choco install sbt

# Or via Scoop
scoop install docker
scoop install openjdk17
scoop install clojure
scoop install sbt
```

**Defined in `deps.edn`:**

```clojure
{:deps {ring/ring-core {:mvn/version "1.12.1"}
        ring/ring-jetty-adapter {:mvn/version "1.12.1"}
        metosin/reitit-ring {:mvn/version "0.7.0"}
        cheshire {:mvn/version "5.12.0"}
        com.taoensso/carmine {:mvn/version "3.3.2"}}}
```

### What Each Library Does
| Library | Purpose |
|----------|----------|
| **Ring** | Core abstraction for HTTP requests and responses in Clojure. |
| **Jetty Adapter** | Provides a web server implementation to run the Ring app. |
| **Reitit** | Fast, data-driven routing library for Ring. |
| **Cheshire** | JSON encoder/decoder for handling request/response bodies. |
| **Carmine** | Redis client for Clojure, used for caching bitonic sequences. |

---

## 🚀 Running the Application

### Option 1: Docker (Recommended)

**Steps:**
1. Navigate to the project root:
   ```bash
   cd rest-api
   ```
2. Start the application and Redis:
   ```bash
   docker-compose up --build
   ```
3. The API will be available at `http://localhost:3000`

### Option 2: Local Development

**Steps:**
1. Start Redis:
   ```bash
   redis-server
   ```
2. Navigate to the project root:
   ```bash
   cd rest-api
   ```
3. Run the app:
   ```bash
   clj -M -m api.main
   ```

---

## 📋 API Endpoints

### Generate Bitonic Sequence
**GET/POST** `/bitonic`

**Query Parameters (GET):**
- `n`: Length of sequence (integer)
- `start`: Range start (integer)
- `end`: Range end (integer)

**JSON Body (POST):**
```json
{"n": 8, "start": 2, "end": 5}
```

**Response:**
```json
{
  "sequence": [2, 3, 4, 5, 5, 4, 3, 2],
  "length": 8,
  "range": [2, 5],
  "cached": false
}
```

### Health Check
**GET** `/health`

**Response:**
```json
{"status": "ok", "service": "bitonic-api"}
```

### Clear Cache
**DELETE** `/cache/clear`

**Response:**
```json
{"message": "Cache cleared"}
```

---

## 🧪 Running Tests

### Unit Tests
```bash
clj -X:test
```

### Performance Tests
```bash
cd ../performance-tests
sbt "Gatling / test"
```

**Note:** Performance tests require Java 11-21. If using Java 25, install Java 17 for Gatling compatibility.

---

## 🐳 Docker Commands

**Build and start:**
```bash
docker-compose up --build
```

**Stop containers:**
```bash
docker-compose down
```

**View logs:**
```bash
docker-compose logs -f api
docker-compose logs -f redis
```

---

## 🏗️ Architecture

- **Entry Point**: `main.clj` - Application bootstrap
- **HTTP Layer**: `controller.clj` - Ring + Reitit for routing
- **Business Logic**: `bitonic_service.clj` - Pure functions for sequence generation
- **Data Layer**: `db.clj` - Redis caching operations
- **Testing**: Unit tests with mocking for Redis operations
- **Performance**: Gatling load tests with POST requests
- **Containerization**: Docker + Docker Compose for deployment

---

## 🧩 Next Steps

* Add request validation with `malli`
* Add API rate limiting
* Implement health checks for Redis connectivity
* Add structured logging
* Add metrics and monitoring

---

**Author:** Bitonic Sequence REST API
**License:** MIT