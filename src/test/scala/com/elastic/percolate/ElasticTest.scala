package com.elastic.percolate

import java.util.UUID

import org.apache.http.HttpHost
import org.elasticsearch.action.DocWriteResponse
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.client.{RestClient, RestHighLevelClient}
import org.elasticsearch.common.xcontent.XContentFactory

import scala.util.Try


object TestElasticSearchPercolate {

  def main(args: Array[String]): Unit = {


    // configure the client
    val restClient = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")))


    // create an index
    val indexNameToCreate = "letxchange"

    Try {
      val requestCreateIndex = new CreateIndexRequest("letxchange")
      restClient.indices().create(requestCreateIndex)
    }.fold(_ => println(s"Index $indexNameToCreate already exist!"), _ => println(s"Index $indexNameToCreate created!"))

    // Index a document
    val productId = UUID.randomUUID().toString

    val builder = XContentFactory.jsonBuilder
    builder.startObject
    builder.field("product-id", productId)
    builder.array("key-words", "iphone", "apple")
    builder.array("tradable-keywords", "samsung", "X5")
    builder.endObject

    val indexRequest = new IndexRequest("letxchange", "products", productId).source(builder)
    if (restClient.index(indexRequest).getResult == DocWriteResponse.Result.CREATED) {
      println(s"Document indexed: ${builder.toString}")
    } else {
      println(s"Error indexing document: ${builder.toString}")
    }


    // close client
    restClient.close()
  }

}
