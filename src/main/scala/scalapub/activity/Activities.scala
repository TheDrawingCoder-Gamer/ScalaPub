package scalapub.activity

import java.net.URL
import cats.data.NonEmptyList
import io.circe.Json
import java.time.{OffsetDateTime, Duration}
enum PubType {
  case Object
  case Link
  case Activity
  case Person
  case Actor
  case Note
  case Follow
  case Like
  case Create
}

type Text = Either[RDFLangText, String]
case class RDFLangText(language: String, value: String)

case class PubBase(
    id: Option[URL],
    context: Option[NonEmptyList[AsBase]],
    name: Option[NonEmptyList[Text]],
    mediaType: Option[String],
    preview: Option[NonEmptyList[AsBase]],
    unparsed: Map[String, Json]

  ) 
trait AsBase {
  def baseRef: PubBase
  def withBaseRef(ref: PubBase): this.type 

  def id: Option[URL] = baseRef.id
  def withId(id: Option[URL]): this.type = withBaseRef(baseRef.copy(id = id))

  def context: Option[NonEmptyList[AsBase]] = baseRef.context
  def withContext(context: Option[NonEmptyList[AsBase]]): this.type = withBaseRef(baseRef.copy(context = context))

  def name: Option[NonEmptyList[Text]] = baseRef.name
  def withName(name: Option[NonEmptyList[Text]]): this.type = withBaseRef(baseRef.copy(name = name))

  def mediaType: Option[String] = baseRef.mediaType
  def withMediaType(mediaType: Option[String]): this.type = withBaseRef(baseRef.copy(mediaType = mediaType))

  def preview: Option[NonEmptyList[AsBase]] = baseRef.preview
  def withPreview(preview: Option[NonEmptyList[AsBase]]): this.type = withBaseRef(baseRef.copy(preview = preview))

  def unparsed: Map[String, Json] = baseRef.unparsed
  def withUnparsed(unparsed: Map[String, Json]): this.type = withBaseRef(baseRef.copy(unparsed = unparsed))
}
case class OneOrMany[T](underlying: Option[NonEmptyList[T]])
type Audience = Option[OneOrMany[AsBase]]
type Many[T] = Option[OneOrMany[T]]
type Link = Either[URL, AsLink]
case class PubObject(
    base: PubBase,
    attachment: Many[AsBase],
    attributedTo: Many[AsBase],
    audience: Audience,
    content: Many[Text],
    summary: Many[Text],
    url: Many[Link],
    generator: Many[AsBase],
    icon: Many[AsBase],
    image: Many[AsBase],
    location: Many[AsBase],
    tag: Many[AsBase],
    startTime: Option[OffsetDateTime],
    endTime: Option[OffsetDateTime],
    duration: Option[Duration],
    published: Option[OffsetDateTime],
    updated: Option[OffsetDateTime],
    inReplyTo: Many[AsBase],
    replies: Many[AsBase],
    to: Audience,
    cc: Audience,
    bto: Audience,
    bcc: Audience,
  )
case class PubLink(
    base: PubBase,
    href: Option[URL],
    hreflang: Option[String],
    rel: Option[NonEmptyList[String]]
  )
trait AsObject extends AsBase {
  def baseRef: PubBase = objectRef.base
  def withBaseRef(ref: PubBase): this.type = withObjectRef(objectRef.copy(base = ref))

  def objectRef: PubObject
  def withObjectRef(ref: PubObject): this.type


}
trait AsLink extends AsBase {
  def baseRef: PubBase = linkRef.base
  def withBaseRef(ref: PubBase): this.type = withLinkRef(linkRef.copy(base = ref))

  def linkRef: PubLink
  def withLinkRef(ref: PubLink): this.type
}


