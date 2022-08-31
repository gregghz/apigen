package com.gregghz.openapi

import java.net.URL
import cats.data.NonEmptyList
import io.circe.Json
import java.net.URI

opaque type Paths = Map[String, PathItem]

case class OpenApi(
    openapi: String,
    info: Info,
    paths: Option[Paths],
    components: Option[Components],
    webhooks: Option[Map[String, PathItem | Reference]]
)

case class Info(
    title: String,
    summary: Option[String],
    description: Option[String],
    termsOfService: Option[URL],
    contact: Option[Contact],
    license: Option[License],
    version: String
)

case class PathItem(
    `$ref`: Option[String],
    summary: Option[String],
    description: Option[String],
    get: Option[Operation],
    put: Option[Operation],
    post: Option[Operation],
    delete: Option[Operation],
    options: Option[Operation],
    head: Option[Operation],
    patch: Option[Operation],
    trace: Option[Operation],
    servers: Option[List[Server]],
    parameters: Option[List[Parameter | Reference]]
)

case class Operation(
    tags: List[String],
    summary: Option[String],
    description: Option[String],
    externalDocs: Option[ExternalDocumentation],
    operationId: Option[String],
    parameters: List[Parameter | Reference],
    requestBody: Option[RequestBody | Reference],
    responses: Responses,
    callbacks: Option[Map[String, Callback | Reference]],
    deprecated: Option[Boolean],
    security: Option[List[SecurityRequirement]],
    servers: Option[List[Server]]
)

case class Components(
    schemas: Option[Map[String, Schema]],
    responses: Option[Map[String, Response | Reference]],
    parameters: Option[Map[String, Parameter | Reference]],
    examples: Option[Map[String, Example | Reference]],
    requestBodies: Option[Map[String, RequestBody | Reference]],
    headers: Option[Map[String, Header | Reference]],
    securitySchemas: Option[Map[String, SecurityScheme | Reference]],
    links: Option[Map[String, Link | Reference]],
    callbacks: Option[Map[String, Callback | Reference]],
    pathItems: Option[Map[String, PathItem | Reference]]
)

case class Reference(
    `$ref`: String,
    summary: Option[String],
    description: Option[String]
)

case class Contact(
    name: Option[String],
    url: Option[URI],
    email: Option[String]
)

case class License(
    name: String,
    identifier: Option[String],
    url: Option[URI]
)

case class Server(
    url: URI,
    description: Option[String],
    variables: Option[Map[String, ServerVariable]]
)

case class ServerVariable(
    `enum`: Option[NonEmptyList[String]],
    default: String,
    description: Option[String]
)

case class Parameter(
    name: String,
    in: ParameterIn,
    description: Option[String],
    requried: Option[Boolean], // defaultl false
    deprecated: Option[Boolean], // default false
    allowEmptyValue: Option[Boolean],
    style: Option[String],
    explode: Option[Boolean],
    allowReserved: Option[Boolean],
    schema: Option[Schema],
    example: Json,
    examples: Map[String, Example | Reference]
)

case class Header(
    description: Option[String],
    requried: Option[Boolean], // defaultl false
    deprecated: Option[Boolean], // default false
    allowEmptyValue: Option[Boolean],
    style: Option[String],
    explode: Option[Boolean],
    allowReserved: Option[Boolean],
    schema: Option[Schema],
    example: Json,
    examples: Map[String, Example | Reference]
)

enum ParameterIn(value: String) {
  case Query extends ParameterIn("query")
  case Header extends ParameterIn("header")
  case Path extends ParameterIn("path")
  case Cookie extends ParameterIn("cookie")
}

case class ExternalDocumentation(
    description: Option[String],
    url: URI
)

case class RequestBody(
    description: Option[String],
    content: Map[String, MediaType],
    required: Option[Boolean] // default false
)

opaque type Responses = Map["default" | Int, Response | Reference]

opaque type Callback = Map[String, PathItem | Reference]

opaque type SecurityRequirement = Map[String, List[String]]

case class Schema(
    discriminator: Option[Discriminator],
    xml: Option[Xml],
    externalDocs: Option[ExternalDocumentation],
    example: Json
)

case class Response(
    description: String,
    headers: Option[Map[String, Header | Reference]],
    content: Option[Map[String, MediaType]],
    links: Option[Map[String, Link | Reference]]
)

case class Example(
    summary: Option[String],
    description: Option[String],
    value: Option[Json],
    externalValue: Option[String]
)

case class SecurityScheme(
    `type`: String,
    description: Option[String],
    name: Option[String],
    in: Option[SecuritySchemeIn],
    scheme: Option[String],
    bearerFormat: Option[String],
    flows: Option[OAuthFlows],
    openIdConnectUrl: Option[String]
)

enum SecuritySchemeIn(value: String) {
  case Query extends SecuritySchemeIn("query")
  case Header extends SecuritySchemeIn("header")
  case Cookie extends SecuritySchemeIn("cookier")
}

case class Link(
  operationRef: Option[String],
  operationId: Option[String],
  parameters: Option[Map[String, Json | String]],
  requestBody: Option[Json | String],
  description: Option[String],
  server: Option[Server],
)

case class MediaType(
  schema: Option[Schema],
  example: Option[Json],
  examples: Option[Map[String, Example | Reference]],
  encoding: Option[Map[String, Encoding]],
)

case class Discriminator(
  propertyName: String,
  mapping: Option[Map[String, String]],
)

case class Xml(
  name: Option[String],
  namespace: Option[String],
  prefix: Option[String],
  attribute: Option[Boolean],
  wrapped: Option[Boolean],
)

case class OAuthFlows(
  `implicit`: Option[OAuthFlow],
  password: Option[OAuthFlow],
  clientCredentials: Option[OAuthFlow],
  authorizationCode: Option[OAuthFlow],
)

case class OAuthFlow(
  authorizationUrl: URI,
  tokenUrl: URI,
  refreshUrl: Option[URI],
  scopes: Map[String, String],
)

case class Encoding(
  contentType: Option[String],
  headers: Option[Map[String, Header | Reference]],
  style: Option[String],
  explode: Option[Boolean],
  allowReserved: Option[Boolean],
)