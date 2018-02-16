package com.elastic.percolate

import java.util.UUID

import org.apache.http.HttpHost
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.client.{RestClient, RestHighLevelClient}
import org.elasticsearch.common.xcontent.XContentFactory._


object TestElasticSearchPercolate {

  def main(args: Array[String]): Unit = {


    // configure the client
    val restClient = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")))


    // create an index
   // val requestCreateIndex = new CreateIndexRequest("letxchange")
   // restClient.indices().create(requestCreateIndex)

    // indexing a document
    val productId = UUID.randomUUID()

    jsonBuilder.startObject("tradable_product")
    jsonBuilder.array("key_words", "apple", "iphone 6")
    jsonBuilder.array("trade_keywords", "Samsung", "S5")
    jsonBuilder.endObject

    val indexRequest = new IndexRequest("letxchange", "producto", productId.toString).source(jsonBuilder)
    restClient.index(indexRequest)


    /*
    val productUUID = UUID.randomUUID()

    val response = client.prepareIndex("productos", "producto")
      .setSource(jsonBuilder()
        .startObject()
        .field("uuid", productUUID.toString)
        .field("owner", "Gerard Vico")
        .field("location", "Spain")
        .array("keywords", "iphone", "apple", "plus")
        .array("trade_keywords", "samsung", "S5")
        .endObject()
      )
      .get()
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
