### REST GUIDE
https://restfulapi.net/

https://stackoverflow.blog/2020/03/02/best-practices-for-rest-api-design/

### Validation in spring boot guide
https://www.youtube.com/watch?v=gPnd-hzM_6A

#### REPO dealing with exceptions
https://github.com/Java-Techie-jt/validation-exception-handling/tree/main

#### Testing Spring Series
1) Unit test - https://www.youtube.com/watch?v=Ae5ukd136pc&list=PLRhHH6sj6xkxp5qxb5g3Rlfj_8lRNK2Qi
2) Simple integration test - https://www.youtube.com/watch?v=NW8i2gna4qA&list=PLRhHH6sj6xkxp5qxb5g3Rlfj_8lRNK2Qi&index=2

https://www.youtube.com/watch?v=GBKY8QyfNDk

#### Creating a Mongodb database
https://www.mongodb.com/basics/create-database

#### Spring Cloud gateway
Intro: https://spring.io/projects/spring-cloud-gateway/
Complete documentation: https://docs.spring.io/spring-cloud-gateway/reference/spring-cloud-gateway.html

#### Keyclock
https://www.keycloak.org/documentation

Docker install:
https://www.keycloak.org/getting-started/getting-started-docker

Command:
sudo docker run -p 8181:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:23.0.4 start-dev

Configuration:
issuer: http://localhost:8181/realms/estudou-realm

#### Docker

Permissions related problems when execute docker-compose and how to fix:
https://docs.docker.com/engine/install/linux-postinstall/#manage-docker-as-a-non-root-user

#### Circuit breaker library in Java

Aparentally is used to garanthe the calls from Promises or another services, in a micro-service architecture

Java: https://resilience4j.readme.io/docs/getting-started
Node: https://github.com/nodeshift/opossum

Docs: https://resilience4j.readme.io/docs/getting-started
Tutorial with tests: https://www.baeldung.com/spring-boot-resilience4j

#### Library for Distributed tracing

Java Distributed Tracing Framework
https://spring.io/projects/spring-cloud-sleuth/

Application to visualize distributed tracing:
https://zipkin.io/

docker run -d -p 9411:9411 openzipkin/zipkin

Micrometer documentation:
https://docs.micrometer.io/micrometer/reference/

Actual reading: https://docs.micrometer.io/micrometer/reference/concepts/naming.html

Documentation to join zipkin and micrometer:
https://www.appsdeveloperblog.com/micrometer-and-zipkin-in-spring-boot/