package org.askucins.filesearcher

import java.io.File

object FileConverter {
  def convertToIOObject(file: File): IOObject =
    if (file.isDirectory) DirectoryObject(file)
    else FileObject(file)
}
