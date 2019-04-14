package org.askucins.filesearcher

class FilterChecker(filter: String) {
  def matches(content: String): Boolean = content.contains(filter)

  def isFileObject(ioObject: IOObject): Boolean = ioObject.isInstanceOf[FileObject]

  def findMatchedFiles(ioObjects: List[IOObject]): List[IOObject] = {
    for (ioObject <- ioObjects
         if matches(ioObject.name) && isFileObject(ioObject))
      yield ioObject
  }
}
