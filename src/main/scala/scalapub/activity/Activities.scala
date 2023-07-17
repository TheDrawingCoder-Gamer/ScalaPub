package scalapub.activity

import java.net.URL

sealed trait Text {
  def str(lang: String): Option[String]
  def strOrDefault(lang: String): String
}
case class LiteralText(literal: String, lang: String = "en") extends Text {
  def str(lang: String): Option[String] = 
    Option.when(lang == this.lang)(literal)
  def strOrDefault(foo: String): String = literal
}

case class TranslatableText(langMap: Map[String, String], defaultLang: String = "en") extends Text {
  def str(lang: String): Option[String] =
    Option.when(langMap.exists(lang))(langMap(lang))
  def strOrDefault(lang: String): String =
    str(lang).getOrElse(langMap(defaultLang))
}

enum PubType {
  case Object
  case Link
  case Activity
  case Person
  case Note
}
trait ABase {
  val id: Option[ABase]
  val name: Option[Text]
  val `type`: PubType

}
case class AObject(id: Option[ABase], name: Option[Text], to: Option[List[String]]) extends ABase {
  override val `type` = PubType.Object

}

case class ALink(href: URL, rel: Option[List[String]], mediaType: Option[String], hreflang: Option[String], height: Option[Int], width: Option[Int], preview: Option[ABase]) extends ABase {
  override val `type` = PubType.Link
}

def LinkURL(href: URL) = ALink(href, None, None, None, None, None, None)

case class PubActivity(id: Option[ABase]

