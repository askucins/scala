package org.askucins.filesearcher

import java.io.File

class Matcher(filter: String, val rootLocation: String = new File(".").getCanonicalPath, checkSubFolders: Boolean = false) {
  val rootIOObject: IOObject = FileConverter.convertToIOObject(new File(rootLocation))

  def execute(): List[String] = {

    //TODO Replace this with a real tail recursion
    def findMatchedFiles(root: IOObject, accumulator: List[IOObject]): List[IOObject] = {
      root match {
        case file: FileObject if FilterChecker(filter) matches file.name => accumulator ++ List(file)
        case directory: DirectoryObject =>
          if (checkSubFolders)
            (directory.children() map (it => findMatchedFiles(it, accumulator))).reduce(_ ++ _)
          else
            accumulator ++ (FilterChecker(filter) findMatchedFiles directory.children())
        case _ => List()
      }
    }

    val matchedFiles = findMatchedFiles(rootIOObject, List())
    matchedFiles map (ioObject => ioObject.name)
  }
}
