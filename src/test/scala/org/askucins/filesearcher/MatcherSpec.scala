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

  "Matcher using a directory containing one file matching the filter" should
    "return a list with that file name" in {
    val matcher = new Matcher("txt", new File("testFiles").getCanonicalPath)
    val results = matcher.execute()
    assert(results == List("readme.txt"))
  }

  "Matcher using a directory containing no files matching the filter" should
    "return an empty list" in {
    val matcher = new Matcher("txt", new File("testFilesX").getCanonicalPath)
    val results = matcher.execute()
    assert(results == List())
  }

  "Matcher that is not passed a root file location" should
    "use the current location" in {
    val matcher = new Matcher("txt")
    assert(matcher.rootLocation == new File(".").getCanonicalPath)
  }

  "Matcher with sub folders with some files matching the filter and some not" should
    "return a list with those matching files names" in {
    val searchSubDirectories = true
    val matcher = new Matcher("txt", new File("testFiles").getCanonicalPath, searchSubDirectories)
    val results = matcher.execute()
    assert(results.sorted == List("readme.txt", "readmeA.txt", "readmeA2.txt", "readmeB.txt", "readmeBB.txt", "readmeC.txt", "readmeC2.txt"))
  }

  "Matcher given a path that has one file that matches the filter and content filter" should
    "return a list with that file name" in {
    val searchSubDirectories = true
    val matcher = new Matcher("data", new File("testFiles").getCanonicalPath, searchSubDirectories, Some("pluralsight"))
    val results = matcher.execute()
    assert(results.sorted == List("readme.data"))
  }

  "Matcher given a path that has files that matched the filter but not the content filter" should
    "return an empty list" in {
    val searchSubDirectories = true
    val matcher = new Matcher("txt", new File("testFiles").getCanonicalPath, searchSubDirectories, Some("pluralsight"))
    val results = matcher.execute()
    assert(results.sorted == List())
  }

  "Matcher given a path that has no files that matched the filter and content filter is provided" should
    "return an empty list" in {
    val searchSubDirectories = true
    val matcher = new Matcher("thereisnosuchfile", new File("testFiles").getCanonicalPath, searchSubDirectories, Some("pluralsight"))
    val results = matcher.execute()
    assert(results.sorted == List())
  }

}
