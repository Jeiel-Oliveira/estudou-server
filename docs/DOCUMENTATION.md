### REST GUIDE
https://restfulapi.net/

https://stackoverflow.blog/2020/03/02/best-practices-for-rest-api-design/

### Validation in spring boot guide
https://www.youtube.com/watch?v=gPnd-hzM_6A

#### Spring Cloud gateway
Intro: https://spring.io/projects/spring-cloud-gateway/
Complete documentation: https://docs.spring.io/spring-cloud-gateway/reference/spring-cloud-gateway.html

#### Spring Jobs
Need to check in the future to know if is something i need to use
https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/autoconfigure/batch/JobLauncherApplicationRunner.html

#### REPO dealing with exceptions
https://github.com/Java-Techie-jt/validation-exception-handling/tree/main

#### Testing Spring Series
1) Unit test - https://www.youtube.com/watch?v=Ae5ukd136pc&list=PLRhHH6sj6xkxp5qxb5g3Rlfj_8lRNK2Qi
2) Simple integration test - https://www.youtube.com/watch?v=NW8i2gna4qA&list=PLRhHH6sj6xkxp5qxb5g3Rlfj_8lRNK2Qi&index=2

https://www.youtube.com/watch?v=GBKY8QyfNDk

#### Creating a Mongodb database
https://www.mongodb.com/basics/create-database

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

#### Spring and Apache Kafka

kafka and docker setup: https://www.baeldung.com/ops/kafka-docker-setup
spring and kafka: https://spring.io/projects/spring-kafka/

Kafka story:
https://techbeacon.com/app-dev-testing/what-apache-kafka-why-it-so-popular-should-you-use-it#:~:text=Kafka's%20origin%20story%20at%20LinkedIn&text=The%20problem%20they%20originally%20set,%22real%2Dtime%22%20processing.

### Configure MYSQL with test containers

https://medium.com/@anil.java.story/springboot-test-containers-mysql-e0350645d608

### CRUD com migrations
https://www.youtube.com/watch?v=tP6wtEaCnSI

Minuto 50: id sendo gerado no padrão UUID
Sempre salvar os valores monetários como centavos (Front-end responsável por formatar - baita de um big brains move)

### CommandLineRunner e suas aplicações

Resposavel por rodar na inicialização do servidor

Com o decorator @Component funciona (ainda não sei muito bem como) quando inicializa o servidor. Exemplos em MissionInitializer

https://www.javaguides.net/2020/02/spring-boot-commandlinerunner-example.html#:~:text=CommandLineRunner%20is%20an%20interface%20used,the%20CommandLineRunner%20interface%20at%20JavaDoc.


#### Response error handler
https://bootcamptoprod.com/customizing-404-response-springboot/

webclient error handler:
https://mohosinmiah1610.medium.com/error-handling-with-webclient-in-spring-boot-e604733071e0