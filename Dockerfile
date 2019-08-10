FROM java:8
EXPOSE 8080
ADD /target/create-business-0.0.1-SNAPSHOT.jar create-business-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/create-business-0.0.1-SNAPSHOT.jar"]