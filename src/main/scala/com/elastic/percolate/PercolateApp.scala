package com.elastic.percolate

import com.elastic.percolate.infrastructure.fixture_creator.ListingFixtureCreator
import org.apache.http.HttpHost
import org.elasticsearch.client.{RestClient, RestHighLevelClient}

object PercolateApp {
  def main(args: Array[String]): Unit = {
    val restClient = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")))

    val listings = ListingFixtureCreator.create(10)

    println("pepe")
  }
}
