package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

import models.UrlStorage
import java.net.{MalformedURLException, URL}

object Application extends Controller {

  val urlForm = Form(
    "url" -> nonEmptyText.verifying("Correct URL", validateUrl(_))
  )
  
  def index = Action {
    Ok(views.html.index(UrlStorage.urls, urlForm))
  }

  def redirectTo(alias : String) = Action {
    UrlStorage.findUrl(alias) match {
      case Some(url) => Redirect(url)
      case _ =>
        Ok(views.html.unknown("Unknown URL"))
    }
  }

  def registerUrl = Action {implicit request =>
    urlForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index(UrlStorage.urls, errors)),
      url => {
        val shortened = UrlStorage.addUrl(url)
        println(url + " -> " + shortened)

        //Ok(views.html.index(shortened, urlForm))
        Redirect(routes.Application.index())
      }
    )
  }

  private def validateUrl(url: String): Boolean = {
    val valid =
      try {
        new URL(url)

        true
      }
      catch {
        case e: MalformedURLException => false
      }

    valid
  }
}