package controllers

import play.api.Play.current
import play.api.mvc.{Action, Controller}
import play.api.{Logger, Play, Routes}

import scala.concurrent.Future

object WikipediaApi extends Controller {

  private val logger = Logger(WikipediaApi.getClass)

  private val WikipediaApiPageSearchUrl: String = Play.configuration.getString("wikipedia-api.lookup").get

  private val WikipediaApiPageGetUrl: String = Play.configuration.getString("wikipedia-api.page").get

  def vcard() = Action.async(parse.json) { request =>
    Future.successful(NotFound)
  }

  def javascriptRoutes() = Action.async { implicit request =>
    Future.successful {
      Ok {
        Routes.javascriptRouter("wikipediaApiJavascriptRoutes") (
          routes.javascript.WikipediaApi.vcard
        )
      }
    }
  }

}
