package linkextractor

import org.jsoup.Jsoup
import kotlinx.serialization.Serializable

@Serializable
data class LinkData(val text: String, val href: String)

class LinkExtractor {
  companion object {
    fun extractLinks(url: String): List<LinkData> {
      val doc = Jsoup.connect(url).get()
      
      return doc.select("a[href]").map {
        LinkData(
        text = if (it.hasText()) { it.text() } else { "[IMG]" },
        href = it.attr("abs:href")
        )
      }
    }
  }
}
