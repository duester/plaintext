package ru.duester.plaintext.model

/** Plain text document representation (without any formatting)
  *
  * @param paragraphs
  *   list of paragraphs (if any)
  * @param metadata
  *   map of document metadata (if any)
  */
case class PlainTextDocument(
    paragraphs: List[Paragraph] = Nil,
    metadata: Map[String, String] = Map.empty
)

/** Single paragraph (without line breaks)
  *
  * @param text
  *   text content
  */
case class Paragraph(text: String)
