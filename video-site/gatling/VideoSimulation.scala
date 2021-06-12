package video

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._


class VideoSimulation extends Simulation {

    val httpProtocol = http
        .baseUrl("http://localhost:8080")
        .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
        .doNotTrackHeader("1")
        .acceptLanguageHeader("en-US,en;q=0.5")
        .acceptEncodingHeader("gzip, deflate")
        .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

    val get480pQuality = exec(http("Get 480p quality").get("/video/2?quality=480"))

    val getOriginQuality = exec(http("Get origin quality").get("/video/2"))

    val scn = scenario("Test Video GET")
        .exec(get480pQuality,
              getOriginQuality)

    setUp(scn.inject(atOnceUsers(50)).protocols(httpProtocol))
}