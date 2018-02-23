package com.elastic.percolate.domain

final case class ListingMatching(id: ListingId, matches: Seq[ListingId])
