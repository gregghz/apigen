package com.gregghz.gen

import scala.quoted.*
import org.http4s.*

object OpenApiGen {
  trait OpenApiEv

  inline def http4s[F[_]](inline method: String, inline path: String)(inline f: Request[F] => F[Response[F]])
  : PartialFunction[Request[F], F[Response[F]]] = ${ http4sImpl('method, 'path, 'f) }

  def http4sImpl(methid: Expr[String], path: Expr[String])(
      using Quotes
  ) = '{
    ???
  }
}
