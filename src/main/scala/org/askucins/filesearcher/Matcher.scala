package org.askucins.filesearcher

import java.io.File

class Matcher(filter: String, val rootLocation: String = new File(".").getCanonicalPath, checkSubFolders: Boolean = false) {
  val rootIOObject: IOObject = FileConverter.convertToIOObject(new File(rootLocation))

  def execute(): List[String] = {

    //TODO Replace this with tail recursion

    def findMatchedFiles(root: IOObject): List[IOObject] = {
      println(root.name)
      root match {
        case file: FileObject if FilterChecker(filter) matches file.name => List(file)
        case directory: DirectoryObject =>
          if (checkSubFolders)
            (directory.children() map (it => findMatchedFiles(it))).reduce(_ ++ _)
          else
            FilterChecker(filter) findMatchedFiles directory.children()
        case _ => List()
      }
    }

    val matchedFiles = findMatchedFiles(rootIOObject)
    matchedFiles map (ioObject => ioObject.name)
  }
}
