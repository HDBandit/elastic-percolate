package com.elastic.percolate.domain

final case class ListingId(value: String)

final case class ListingName(value: String)

final case class ListingKeywords(value: Seq[String])

final case class ListingTradableKeywords(value: Seq[String])

final case class Listing(
    id: ListingId,
    name: ListingName,
    keywords: ListingKeywords,
    tradableKeywords: ListingTradableKeywords
)
