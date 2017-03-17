package com.excilys.computerdatabase.gatling.process

import com.typesafe.config.ConfigFactory
import io.gatling.core.Predef._
import io.gatling.http.Predef._

/**
  * Created by Cédric Cousseran on 29/03/16.
  * Delete the computer which was edited before.
  */
object Delete {
  val config = ConfigFactory.load()

  val delete = exec(http("Delete: Search for delete")
    .get(config.getString("application.urls.dashboardPage")+"?action=Filter&search="+"${addComputerName}_edited")
    .check(
      status.is(200),
      css("#results input", "value").saveAs("computerId")
    ))
    .pause(3, 10)
    .exec(http("Delete: Delete post")
      .post(config.getString("application.urls.deletePost"))
      .formParam("delete", "${computerId}")
    ).pause(3, 10)
}
