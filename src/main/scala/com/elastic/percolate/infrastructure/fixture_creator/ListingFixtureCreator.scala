package com.elastic.percolate.infrastructure.fixture_creator

import scala.util.Random

import com.elastic.percolate.domain._

object ListingFixtureCreator {
  def create(number: Int): Seq[Listing] = {
    (1 to number).map { _ =>
      val id = Random.alphanumeric.take(10).mkString
      val name = Random.alphanumeric.take(10).mkString
      val keyword = Random.alphanumeric.take(10).mkString
      val tradablekeyword = Random.alphanumeric.take(10).mkString

      Listing(
        ListingId(id),
        ListingName(name),
        ListingKeywords(Seq(keyword)),
        ListingTradableKeywords(Seq(tradablekeyword)))
    }
  }
}
