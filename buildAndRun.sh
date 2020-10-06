#!/bin/sh
mvn clean package && docker build -t com.airhacks/cluster-test .
docker rm -f cluster-test || true && docker run -d -p 8080:8080 -p 4848:4848 --name cluster-test com.airhacks/cluster-test 
