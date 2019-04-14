package org.askucins.filesearcher

import java.io.File

import org.scalatest._

class MatcherSpec extends FlatSpec {

  "Matcher that is passed a file matching the filter" should
    "return a list with that file name " in {
    val matcher = new Matcher("fake", "fakePath")
    val results = matcher.execute()
    assert(results == List("fakePath"))
  }

  "Matcher using a directory containing one file matching to the filter" should
    "return a list with that file name" in {
    val matcher = new Matcher("txt", new File("testFiles").getCanonicalPath())
    val results = matcher.execute()
    assert(results == List("readme.txt"))
  }

  "Matcher using a directory containing no files matching to the filter" should
    "return an empty list" in {
    val matcher = new Matcher("txt", new File("testFilesX").getCanonicalPath())
    val results = matcher.execute()
    assert(results == List())
  }

  "Matcher that is not passed a root file location" should
    "use the current location" in {
    val matcher = new Matcher("txt")
    assert(matcher.rootLocation == new File(".").getCanonicalPath)
  }
}
