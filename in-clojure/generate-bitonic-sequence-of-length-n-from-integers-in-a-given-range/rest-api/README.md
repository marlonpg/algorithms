# Goals of this POC
- ✅ unit tests
- performance tests / benchmarks
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
    ├── core.clj        ; Application entry point - starts Jetty server
    ├── routes.clj      ; Defines routes and HTTP handlers
    ├── handlers.clj    ; Business logic for bitonic sequence generation
    └── db.clj          ; Redis database operations
test/
└── api/
    ├── handlers_test.clj ; Unit tests for handlers
    └── db_test.clj       ; Unit tests for database operations
deps.edn                  ; Project dependencies
Dockerfile                ; Docker container configuration
docker-compose.yml        ; Multi-container orchestration
```

---

## ⚙️ Dependencies

Defined in `deps.edn`:

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

**Prerequisites:**
* Docker and Docker Compose installed

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

**Prerequisites:**
* Java (JDK 8 or later)
* Clojure CLI tools
* Redis server running locally

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
   clj -M -m api.core
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
{"n": 6, "start": 1, "end": 3}
```

**Response:**
```json
{
  "sequence": [1, 2, 3, 3, 2, 1],
  "length": 6,
  "range": [1, 3],
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

## 🧪 Running Tests

```bash
clj -X:test
```

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

## 🏗️ Architecture

- **API Layer**: Ring + Reitit for HTTP handling
- **Business Logic**: Pure functions for bitonic sequence generation
- **Caching**: Redis for storing computed sequences
- **Containerization**: Docker + Docker Compose for deployment
- **Testing**: Clojure.test with comprehensive unit tests

## 🧩 Next Steps

* Add request validation with `malli`
* Implement performance benchmarks
* Add API rate limiting
* Implement health checks for Redis connectivity
* Add logging with structured output

---

**Author:** Bitonic Sequence REST API
**License:** MIT
