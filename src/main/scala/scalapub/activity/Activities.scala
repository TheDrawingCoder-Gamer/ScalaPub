package scalapub.activity

import java.net.URL

enum PubType {
  case Object
  case Link
  case Activity
  case Person
  case Actor
  case Note
  case Follow
  case Like
}

sealed trait Text {
  def translate(lang: String): String
}
case class TranslatableText(langMap: Map[String, String], default: String = "en") {
  def translate(lang: String): String =
    Option.when(langMap.exists(lang))(langMap(lang)).getOrElse(langMap(default))
}
case class LiteralText(literal: String) {
  def translate(lang: String): String = literal
}
type NonFunctional[A] = Either[List[A], A]
sealed trait PubBase {
  val id: Option[URL]
  val name: Option[Text]
  // While type is technically non functional, it's easier to consider it functional as we won't be dealing with any extension types.
  val `type`: PubType
}
trait TObject {
  
}
case class CoreFields(id: Option[URL], name: Option[Text]) 
sealed case class PubObject(id: Option[URL], name: Option[Text], to: Option[List[String]], content: Option[Text]) extends PubBase {
  override val `type` = PubType.Object
}

sealed case class PubNote(id: Option[URL], name: Option[Text], to: Option[List[String]], content: Option[Text]) extends PubObject(id, name, to, content) {
  override val `type` = PubType.Note
}















