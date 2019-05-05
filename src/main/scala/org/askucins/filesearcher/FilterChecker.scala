package org.askucins.filesearcher

import java.io.File

class FilterChecker(filter: String) {
  def matches(content: String): Boolean = content contains filter

  def isFileObject(ioObject: IOObject): Boolean = ioObject.isInstanceOf[FileObject]

  def findMatchedFiles(ioObjects: List[IOObject]): List[IOObject] =
    for (ioObject <- ioObjects
         if matches(ioObject.name)
         if isFileObject(ioObject))
      yield ioObject

  def matchesFileContent(file: File): Boolean = {
    import scala.io.Source
    def source = Source.fromFile(file)

    val hasMatch = source.getLines().exists(it => matches(it))
    source.close()
    hasMatch
  }
}

object FilterChecker {
  def apply(filter: String): FilterChecker = new FilterChecker(filter)
}