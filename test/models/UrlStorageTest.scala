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

    val short1 = storage.addUrl("http://github.com")
    val short2 = storage.addUrl("http://meetup.com")

    storage.findUrl(short1) must be(Some("http://github.com"))
    storage.findUrl(short2) must be(Some("http://meetup.com"))

    storage.findUrl("unknown") must be(None)
  }

}