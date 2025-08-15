package ru.duester.plaintext.parser

import fastparse.*
import fastparse.Parsed.Failure
import fastparse.Parsed.Success
import ru.duester.plaintext.model.Paragraph
import ru.duester.plaintext.model.PlainTextDocument
import zio.IO
import zio.ZIO

import NoWhitespace.*

/** Parser for plain text document
  */
object Parser:
  private def paragraph[$: P]: P[Paragraph] =
    P(CharsWhile(_ != '\n', min = 0).!.map(_.trim())).map(Paragraph(_))

  private def document[$: P]: P[PlainTextDocument] =
    P(paragraph.rep(sep = "\n") ~ End).map { case paragraphs =>
      PlainTextDocument(paragraphs.filter(_.text.nonEmpty).toList)
    }

  /** Parse plain text document
    *
    * @param text
    *   text to parse
    */
  def parse(text: String): IO[String, PlainTextDocument] =
    fastparse.parse(text, { case given P[_] => document }) match
      case Success(document, _) => ZIO.succeed(document)
      case f: Failure           => ZIO.fail(s"Parsing error: ${f.msg}")
