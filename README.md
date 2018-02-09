# elastic-percolate
Proyecto para probar funcionalidades de elastic


## Paso 1
======
Para arrancar el Elastic Search en local ejecutar:
> docker run -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" docker.elastic.co/elasticsearch/elasticsearch:6.2.1

Link de referencia:
https://www.elastic.co/guide/en/elasticsearch/reference/current/docker.html


## Paso 2
======
Para ejecutar el test de Elastic, ejecutar desde la consola de sbt:

> test:runMain com.elastic.percolate.TestElasticSearchPercolate
