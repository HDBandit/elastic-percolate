package com.elastic.percolate

import org.apache.http.HttpHost
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest
import org.elasticsearch.client.{RestClient, RestHighLevelClient}

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


    // close client
    restClient.close()
  }

}
