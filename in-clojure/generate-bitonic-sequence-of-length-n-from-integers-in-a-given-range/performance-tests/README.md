# Bitonic API Performance Tests

Performance testing suite for the Bitonic Sequence REST API using Gatling.

## Prerequisites

- Java 8 or later
- Bitonic API running on `http://localhost:3000`

## Running Tests

### Option 1: Using SBT

1. Start the Bitonic API:
   ```bash
   cd ../rest-api
   docker-compose up
   ```

2. Run performance tests:
   ```bash
   sbt "Gatling / test"
   ```

### Option 2: Manual Gatling Download

1. Download Gatling from: https://gatling.io/open-source/
2. Extract to any folder
3. Copy `src/test/scala/BitonicApiSimulation.scala` to `gatling/user-files/simulations/`
4. Run: `gatling/bin/gatling.bat -s BitonicApiSimulation`

## Test Scenarios

The simulation tests:
- **Health Check**: Verifies API availability
- **GET Requests**: Tests query parameter handling
- **POST Requests**: Tests JSON body processing
- **Caching**: Verifies Redis caching behavior

## Load Profile

- **Warm-up**: 10 concurrent users
- **Ramp-up**: 50 users over 30 seconds
- **Sustained Load**: 20 users/second for 60 seconds

## Reports

After running tests, HTML reports are generated in:
- **Standalone**: `gatling-charts-highcharts-bundle-3.8.4/results/`
- **SBT**: `target/gatling/results/`

## Manual Testing Alternative

```bash
# Health check
curl http://localhost:3000/health

# Single request test
curl "http://localhost:3000/bitonic?n=6&start=1&end=3"
```