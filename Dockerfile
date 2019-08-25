FROM java:8
ADD /target/create-business-0.0.1-SNAPSHOT.jar create-business-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/create-business-0.0.1-SNAPSHOT.jar"]
