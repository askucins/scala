package org.askucins.filesearcher

import java.io.{File, FileNotFoundException}

import scala.util.control.NonFatal

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
    // Scopes, scopes... hence stacked try-catch blocks!
    try {
      val source = Source.fromFile(file)
      try
        source.getLines() exists (line => matches(line))
      catch { // If something happens during processing of that opened file
        case NonFatal(_) => false
      }
      finally { // We still need to close that file!
        source.close()
      }
    }
    catch { // If something fails on attempt to open a file
      case NonFatal(_) => false
    }
  }
}

object FilterChecker {
  def apply(filter: String): FilterChecker = new FilterChecker(filter)
}