# Clojure REST API Example

This is a minimal REST API built with **Clojure** using **Ring**, **Reitit**, and **Jetty**.
It demonstrates how to structure a small project with clear separation between routes, handlers, and the application entry point.

---

## 📂 Project Structure

```
src/
└── my_api/
├── core.clj        ; Application entry point - starts Jetty server
├── routes.clj      ; Defines routes and HTTP handlers
└── handlers.clj    ; Business logic for each endpoint
deps.edn                  ; Project dependencies
```

---

## ⚙️ Dependencies

Defined in `deps.edn`:

```clojure
{:deps
{ring/ring-core {:mvn/version "1.12.1"}
ring/ring-jetty-adapter {:mvn/version "1.12.1"}
metosin/reitit-ring {:mvn/version "0.7.0"}
cheshire {:mvn/version "5.12.0"}}}
```

### What Each Library Does
| Library | Purpose |
|----------|----------|
| **Ring** | Core abstraction for HTTP requests and responses in Clojure. |
| **Jetty Adapter** | Provides a web server implementation to run the Ring app. |
| **Reitit** | Fast, data-driven routing library for Ring. |
| **Cheshire** | JSON encoder/decoder for handling request/response bodies. |

---

## 🚀 Running the Application

### Prerequisites

* **Java (JDK 8 or later)** installed and available in your PATH.
* **Clojure CLI tools** installed.
  Verify installation:
  ```bash
  clj -Sdescribe
  ```

### Steps

1. Clone or download this project.
2. Navigate to the project root (where `deps.edn` is located):
   ```bash
   cd rest-api
   ```
3. Run the app:
   ```bash
   clj -M -m my-api.core
   ```
4. Open your browser or use `curl`:

   * `GET http://localhost:3000/hello` → returns a JSON hello message
   * `GET http://localhost:3000/` → returns `{"error":"Not found"}`

---

## 🧠 Code Overview

### `handlers.clj`
Defines logic for each route:
```clojure
(defn hello [_]
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body (json/generate-string {:message "Hello, world!"})})
```

### `routes.clj`
Maps endpoints to handlers and adds a default 404 handler:
```clojure
(def app
  (ring/ring-handler
    (ring/router
      [["/hello" {:get handlers/hello}]])))
```

### `core.clj`
Starts the Jetty server:
```clojure
(defn -main []
  (println "Starting server on port 3000...")
  (jetty/run-jetty app {:port 3000 :join? false}))
```

---

## ✅ Example Output

**GET /hello**
```json
{"message":"Hello, world!"}
```

**GET /**
```json
{"error":"Not found"}
```

---

## 🧩 Next Steps

* Add a POST endpoint and parse JSON requests.
* Use `mount` or `integrant` to manage app lifecycle.
* Connect to a database using `next.jdbc`.
* Add request validation with `malli`.

---

**Author:** Example REST API in Clojure
**License:** MIT
