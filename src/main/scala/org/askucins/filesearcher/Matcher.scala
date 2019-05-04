package org.askucins.filesearcher

import java.io.File

import scala.annotation.tailrec

class Matcher(filter: String, val rootLocation: String = new File(".").getCanonicalPath, checkSubFolders: Boolean = false) {
  val rootIOObject: IOObject = FileConverter.convertToIOObject(new File(rootLocation))

  def execute(): List[String] = {
    @tailrec
    def recursiveMatch(files: List[IOObject], currentList: List[FileObject]): List[FileObject] = {
      files match {
        case List() => currentList
        case ioObject :: rest =>
          ioObject match {
            case file: FileObject if FilterChecker(filter) matches file.name =>
              recursiveMatch(rest, file :: currentList)
            case directory: DirectoryObject =>
              recursiveMatch(rest ::: directory.children, currentList)
            case _ => recursiveMatch(rest, currentList)
          }
      }
    }

    val matchedFiles = rootIOObject match {
      case file: FileObject if FilterChecker(filter) matches file.name => List(file)
      case directory: DirectoryObject =>
        if (checkSubFolders) recursiveMatch(directory.children(), List())
        else FilterChecker(filter) findMatchedFiles directory.children()
      case _ => List()
    }

    //Naive implementation
    /*
    def recursiveMatchNaive(root: IOObject): List[IOObject] = {
      root match {
        case file: FileObject if FilterChecker(filter) matches file.name => List(file)
        case directory: DirectoryObject =>
          if (checkSubFolders)
            (directory.children() map (it => recursiveMatchNaive(it))).reduce(_ ++ _)
          else
            FilterChecker(filter) findMatchedFiles directory.children()
        case _ => List()
      }
    }
    val matchedFiles = recursiveMatchNaive(rootIOObject)
    */

    matchedFiles map (ioObject => ioObject.name)
  }
}
