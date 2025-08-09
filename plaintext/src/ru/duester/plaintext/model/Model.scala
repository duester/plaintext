package ru.duester.plaintext.model

case class PlainTextDocument(
    paragraphs: List[Paragraph],
    metadata: Map[String, String] = Map.empty
)

case class Paragraph(text: String)
