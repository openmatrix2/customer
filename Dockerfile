FROM openjdk:8
ADD target/customer-0.0.1.jar customer-0.0.1.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","customer-0.0.1.jar"]