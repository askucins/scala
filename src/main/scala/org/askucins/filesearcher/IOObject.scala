package org.askucins.filesearcher

import java.io.File

trait IOObject {
  val file: File
  val name: String = file.getName
}

case class FileObject(file: File) extends IOObject

case class DirectoryObject(file: File) extends IOObject {
  def children(): List[IOObject] = {
    // Instead of a direct checking whether listFiles() returns anything indeed
    // we may use exception handling
    try
      file.listFiles().toList map (file => FileConverter.convertToIOObject(file))
    catch {
      case _: NullPointerException => List()
    }
  }
}
