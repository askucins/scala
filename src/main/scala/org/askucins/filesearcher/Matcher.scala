package org.askucins.filesearcher

import java.io.File

class Matcher(filter: String, val rootLocation: String = new File(".").getCanonicalPath()) {
  val rootIOObject: IOObject = FileConverter.convertToIOObject(new File(rootLocation))

  def execute(): List[String] = {
    val matchedFiles = rootIOObject match {
      case file: FileObject if FilterChecker(filter) matches file.name => List(file)
      case directory: DirectoryObject =>
        FilterChecker(filter) findMatchedFiles directory.children()
      case _ => List()
    }

    matchedFiles map (ioObject => ioObject.name)
  }
}