package com.gregghz.sample

import org.http4s.HttpRoutes
import cats.effect.IO
import com.gregghz.gen.*

object Main {

  val service = HttpRoutes.of[IO] {
    OpenApiGen.http4s[IO]("GET", "/root") { request =>
      ??? 
    }
  }

  def main(args: Array[String]): Unit = {
    
  }
}
