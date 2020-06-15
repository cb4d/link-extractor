package linkextractor

import org.jsoup.Jsoup

fun main(args: Array<String>) {
  val url = args.first()

  val doc = Jsoup.connect(url).get()

  doc.select("a[href]").forEach { println(it.attr("href")) }
}
