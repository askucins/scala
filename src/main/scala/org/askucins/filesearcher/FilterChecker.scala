package org.askucins.filesearcher

class FilterChecker(filter: String) {
  def matches(content: String): Boolean = content.contains(filter)

  def findMatchedFiles(fileObjects: List[FileObject]): List[FileObject] = {
    for (fileObject <- fileObjects
         if matches(fileObject.name))
      yield fileObject
  }
}
