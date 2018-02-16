package com.elastic.percolate

import java.net.InetAddress
import java.util.{Date, UUID}

import org.elasticsearch.common.settings.Settings
import org.elasticsearch.common.transport.TransportAddress
import org.elasticsearch.transport.client.PreBuiltTransportClient
import org.elasticsearch.common.xcontent.XContentFactory._
import org.elasticsearch.action.search.SearchResponse
import org.elasticsearch.action.search.SearchType
import org.elasticsearch.index.query.QueryBuilders._


object TestElasticSearchPercolate {

  def main(args: Array[String]): Unit = {


    // configure the client
    val client = new PreBuiltTransportClient(Settings.EMPTY)
      .addTransportAddress(new TransportAddress(InetAddress.getLocalHost, 9200))


    println(s"Client connected: ${client.connectedNodes()}")

    // create an index (index a document)
    val tinyHouseUUID = UUID.randomUUID()

    val response = client.prepareIndex("casas", "tiny_house", tinyHouseUUID.toString)
      .setSource(jsonBuilder()
        .startObject()
        .field("owner", "Gerard Vico")
        .field("location", "Spain")
        .field("description", "It is very cool and small")
        .endObject()
      )
      .get()


    // Obtain a document by id
    /* val responseGet = client.prepareGet("twitter", "tweet", tinyHouseUUID.toString).get

    println(s"Get response -> owner: ${responseGet.getField("owner")}; location: ${responseGet.getField("location")}")
    */

    // Search by criteria
    /* val searchResponse = client.prepareSearch("casas")
      .setTypes("tiny_house")
      .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
      .setQuery(QueryBuilders.termQuery("multi", "test"))                 // Query
      .setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))     // Filter
      .setFrom(0).setSize(60).setExplain(true)
      .get() */

  }

}
