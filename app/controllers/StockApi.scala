package controllers

import play.api.Play.current
import play.api.libs.ws.{WS, WSRequestHolder}
import play.api.mvc.{Action, Controller}
import play.api.{Logger, Play, Routes}

import scala.concurrent.Future

trait StockApi { this: Controller =>

  protected val logger: Logger

  protected val SymbolsApiUrl: String

  protected val ChartDataApiUrl: String

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

  protected def callWS(url: String): WSRequestHolder

}

object StockApi extends StockApi with Controller {

  override protected val logger = Logger(classOf[StockApi])

  override protected val SymbolsApiUrl = Play.configuration.getString("markitondemand-api.lookup").get

  override protected val ChartDataApiUrl = Play.configuration.getString("markitondemand-api.chartData").get

  override protected def callWS(url: String) = WS.url(url)

}
