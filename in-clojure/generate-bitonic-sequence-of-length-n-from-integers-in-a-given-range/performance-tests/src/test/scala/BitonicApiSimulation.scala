import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class BitonicApiSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("http://localhost:3000")
    .acceptHeader("application/json")
    .contentTypeHeader("application/json")

  val scn = scenario("Bitonic API Performance Test")
    .exec(
      http("Health Check")
        .get("/health")
        .check(status.is(200))
    )
    .pause(1)
    .exec(
      http("Generate Bitonic Sequence - POST")
        .post("/bitonic")
        .body(StringBody("""{"n": 10, "start": 1, "end": 5}"""))
        .check(status.is(200))
        .check(jsonPath("$.sequence").exists)
        .check(jsonPath("$.length").is("10"))
    )
    .pause(1)
    .exec(
      http("Generate Bitonic Sequence - Different Params")
        .post("/bitonic")
        .body(StringBody("""{"n": 8, "start": 2, "end": 6}"""))
        .check(status.is(200))
        .check(jsonPath("$.sequence").exists)
        .check(jsonPath("$.length").is("8"))
    )
    .pause(1)
    .exec(
      http("Test Caching - Same Request")
        .post("/bitonic")
        .body(StringBody("""{"n": 6, "start": 1, "end": 3}"""))
        .check(status.is(200))
        .check(jsonPath("$.cached").exists)
    )

  setUp(
    scn.inject(
      atOnceUsers(10),
      rampUsers(50) during (30.seconds),
      constantUsersPerSec(20) during (60.seconds)
    )
  ).protocols(httpProtocol)
}