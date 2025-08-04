package ru.duester.plaintext

import ru.duester.plaintext.model.Paragraph
import ru.duester.plaintext.model.PlainTextDocument
import ru.duester.plaintext.parser.Parser
import zio.test.*

object ParserSpec extends ZIOSpecDefault:
  def spec: Spec[TestEnvironment, Any] =
    suite("test parser")(
      test("multiple paragraphs"):
        val expected = PlainTextDocument(
          List(
            Paragraph("First paragraph"),
            Paragraph("Second paragraph"),
            Paragraph("Third paragraph")
          )
        )
        assertZIO(Parser.parse(Data.multiParagraphs))(
          Assertion.equalTo(expected)
        )
      ,
      test("single paragraph"):
        val expected = PlainTextDocument(
          List(Paragraph("Hello world!"))
        )
        assertZIO(Parser.parse(Data.singleParagraph))(
          Assertion.equalTo(expected)
        )
    )

object Data:
  val multiParagraphs = """First paragraph
    |Second paragraph
    |
    |
    |Third paragraph
    |""".stripMargin

  val singleParagraph = "Hello world!"
