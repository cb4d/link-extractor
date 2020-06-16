package linkextractor

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.serialization.*

import linkextractor.LinkExtractor

fun Application.main() {
  install(DefaultHeaders)
  install(CallLogging)
  install(ContentNegotiation) {
    json()
  }
  install(Routing) {
    get("/") {
      call.respondText("Usage: http://<hostname>[:<prt>]/api?url=<url>", ContentType.Text.Html)
    }
    get("/api/") {
      val url = call.request.queryParameters["url"]
      ?: run {
        call.respondText("Usage: http://<hostname>[:<prt>]/api?url=<url>", ContentType.Text.Html)
        return@get
      }

      val links = LinkExtractor.extractLinks(url)
      call.respond(links)
    }
  }
}
