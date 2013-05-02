package models

object UrlStorage extends UrlStorage

//todo think about simultaneous changes
class UrlStorage {
  // URL -> alias
  private var links = Map[String, String]()

  // alias -> URL
  private var aliases = Map[String, String]()

  private var index = 0

  def urls = aliases

  def addUrl(url: String): String = {
    links.get(url) match {
      case Some(short) => short
      case _ => {
        val alias = shortenUrl(url)

        links = links + (url -> alias)
        aliases = aliases + (alias -> url)

        alias
      }
    }
  }

  def findUrl(short: String): Option[String] = aliases.get(short)

  def remove(short: String) {
    findUrl(short) map {url =>
      links = links - url
      aliases = aliases - short
    }
  }

  private def shortenUrl(url: String) = {
    index += 1
    String.valueOf(index)
  }
}
