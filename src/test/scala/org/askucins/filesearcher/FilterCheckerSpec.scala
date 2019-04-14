package org.askucins.filesearcher

import org.scalatest._

class FilterCheckerSpec extends FlatSpec {

  "FilterChecker passed a list where one file matches the filter" should
    "return a list with that file" in {
    val matchingFile = new FileObject("match")
    val listOfFiles = List(new FileObject("random"), matchingFile)
    val matchedFiles = new FilterChecker("match").findMatchedFiles(listOfFiles)
    assert(matchedFiles == List(matchingFile))
  }

  "FilterChecker passed a list with a directory that matchers the filter" should
    "should not return the directory" in {
    val listOfIOObjects = List(new FileObject("random"), new DirectoryObject("match"))
    val matchedFiles = new FilterChecker("match").findMatchedFiles(listOfIOObjects)
    assert(matchedFiles.isEmpty)
  }
}
