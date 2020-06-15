package linkextractor

import org.jsoup.Jsoup

data class LinkData(val text: String, val href: String)

private fun extractLinks(url: String): List<LinkData> {
  val doc = Jsoup.connect(url).get()

  return doc.select("a[href]").map {
    LinkData(
      text = if (it.hasText()) { it.text() } else { "[IMG]" },
      href = it.attr("abs:href")
    )
  }
}

fun main(args: Array<String>) {
  val url = args.firstOrNull()
    ?: run {
      println("usage: java -jar link-extractor <URL>")
      System.exit(1)
      return
    }

  extractLinks(url).forEach { println("[${it.text}](${it.href})") }
}
