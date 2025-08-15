package ru.duester.plaintext

import ru.duester.plaintext.model.Paragraph
import ru.duester.plaintext.model.PlainTextDocument
import ru.duester.plaintext.parser.Parser
import zio.test.*

object ParserSpec extends ZIOSpecDefault:
  def spec: Spec[TestEnvironment, Any] =
    suite("ParserSpec")(
      test("multiple paragraphs"):
        val expected = PlainTextDocument(
          List(
            Paragraph("First paragraph"),
            Paragraph("Second paragraph"),
            Paragraph("Third paragraph")
          )
        )
        for {
          parsed <- Parser.parse(Data.multiParagraphs)
        } yield assert(parsed)(Assertion.equalTo(expected))
      ,
      test("single paragraph"):
        val expected = PlainTextDocument(
          List(Paragraph("Hello world!"))
        )
        for {
          parsed <- Parser.parse(Data.singleParagraph)
        } yield assert(parsed)(Assertion.equalTo(expected))
      ,
      test("empty document"):
        val expected = PlainTextDocument()
        for {
          parsed <- Parser.parse("")
        } yield assert(parsed)(Assertion.equalTo(expected))
    )

object Data:
  val multiParagraphs = """First paragraph
    |Second paragraph
    |
    |
    |Third paragraph
    |""".stripMargin

  val singleParagraph = "Hello world!"
