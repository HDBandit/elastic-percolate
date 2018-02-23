package com.elastic.percolate

import java.util.UUID

import org.apache.http.HttpHost
import org.elasticsearch.action.DocWriteResponse
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.client.{RestClient, RestHighLevelClient}
import org.elasticsearch.common.xcontent.XContentFactory
import org.elasticsearch.action.update.UpdateRequest

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

    val createBuilder = XContentFactory.jsonBuilder
    createBuilder.startObject
    createBuilder.field("product-id", productId)
    createBuilder.array("key-words", "iphone", "apple")
    createBuilder.array("tradable-keywords", "samsung", "X5")
    createBuilder.endObject

    val indexRequest = new IndexRequest("letxchange", "products", productId).source(createBuilder)
    if (restClient.index(indexRequest).getResult == DocWriteResponse.Result.CREATED) {
      println(s"Document indexed: ${createBuilder.prettyPrint().toString} with documentID: $productId")
    } else {
      println(s"Error indexing document: ${createBuilder.prettyPrint().toString}, documentID: $productId")
    }

    // Update an existing indexed document
    val updateBuilder = XContentFactory.jsonBuilder
    updateBuilder.startObject
    updateBuilder.array("tradable-keywords", "samsung", "X5", "bicicleta", "BMX")
    updateBuilder.endObject

    val updateRequest = new UpdateRequest("letxchange", "products", productId).doc(updateBuilder)
    if (restClient.update(updateRequest).getResult == DocWriteResponse.Result.UPDATED) {
      println(s"Document indexed updated: ${createBuilder.prettyPrint().toString} with documentID: $productId")
    } else {
      println(s"Error updating indexed document: ${updateBuilder.prettyPrint().toString}, documentID: $productId")
    }

    // close client
    restClient.close()
  }

}
