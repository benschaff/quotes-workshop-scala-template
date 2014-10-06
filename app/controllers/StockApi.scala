package controllers

import models.StockApi.{ChartRequestElement, ChartRequest, StockSymbol}
import play.api.Play.current
import play.api.cache.Cache
import play.api.libs.json.{JsValue, JsError, Json}
import play.api.libs.ws.WS
import play.api.mvc.{Action, Controller}
import play.api.{Routes, Logger, Play}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration._

object StockApi extends Controller {

  private val logger = Logger(StockApi.getClass)

  private val SymbolsApiUrl: String = Play.configuration.getString("markitondemand-api.lookup").get

  private val ChartDataApiUrl: String = Play.configuration.getString("markitondemand-api.chartData").get

  def symbols(query: String) = Action.async {
    Future.successful(NotFound)
  }

  def last30Days(symbol: String) = Action.async {
    Future.successful(NotFound)
  }

  def javascriptRoutes() = Action.async { implicit request =>
    Future.successful {
      Ok {
        Routes.javascriptRouter("stockApiJavascriptRoutes") (
          routes.javascript.StockApi.symbols,
          routes.javascript.StockApi.last30Days
        )
      }
    }
  }

}
