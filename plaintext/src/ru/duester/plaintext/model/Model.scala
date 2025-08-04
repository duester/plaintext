package ru.duester.plaintext.model

case class PlainTextDocument(paragraphs: List[Paragraph])

case class Paragraph(text: String)
