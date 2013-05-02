package models

object UrlStorage extends UrlStorage

class UrlStorage {
  // URL -> short
  private var links = Map[String, String]()

  // short -> URL
  private var shortLinks = Map[String, String]()

  def urls = shortLinks

  def addUrl(url: String): String = {
    links.get(url) match {
      case Some(short) => short
      case _ => {
        val shortUrl = shortenUrl(url)

        links = links + (url -> shortUrl)
        shortLinks = shortLinks + (shortUrl -> url)

        shortUrl
      }
    }
  }

  def findUrl(short: String): Option[String] = shortLinks.get(short)

  private def shortenUrl(url: String): String = {
    String.valueOf(links.size + 1)
  }
}
