package models

import org.scalatest.FunSuite
import org.scalatest.matchers.MustMatchers._

class UrlStorageTest extends FunSuite {
  test("add new URL to the storage") {
    val storage = new UrlStorage

    storage.addUrl("http://github.com") must be("1")
  }

  test("add 2 URLs to the storage") {
    val storage = new UrlStorage

    storage.addUrl("http://github.com") must be("1")
    storage.addUrl("http://meetup.com") must be("2")
  }

  test("add same URL twice to the storage") {
    val storage = new UrlStorage

    storage.addUrl("http://github.com") must be("1")
    storage.addUrl("http://github.com") must be("1")
  }

  test("find URLs in the storage") {
    val storage = new UrlStorage

    val alias1 = storage.addUrl("http://github.com")
    val alias2 = storage.addUrl("http://meetup.com")

    storage.findUrl(alias1) must be(Some("http://github.com"))
    storage.findUrl(alias2) must be(Some("http://meetup.com"))

    storage.findUrl("unknown") must be(None)
  }

  test("remove a URL") {
    val storage = new UrlStorage

    val alias1 = storage.addUrl("http://github.com")
    val alias2 = storage.addUrl("http://meetup.com")

    storage.remove(alias1)

    storage.findUrl(alias2) must be(Some("http://meetup.com"))
    storage.findUrl(alias1) must be(None)
  }
}