# create-business
Spring boot application used to create a business

# Setup instructions
1. Download jdk 8 - https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
2. Download Maven - http://www.trieuvan.com/apache/maven/maven-3/3.6.1/binaries/apache-maven-3.6.1-bin.zip

# How to setup JDK and Maven on Windows Machine
Follow below steps
https://www.mkyong.com/maven/how-to-install-maven-in-windows/ 

Verify the install by running the below commands from command prompt

java --version
mvn -version

# Running the application
After cloning the project
Cd to create-business
Run the command mvn clean install

You should see a build success message once the build is done.

Run mvn spring-boot:run to start the application.

Once the applciation is up and running. 

Hit http://localhost:8080/create/business/health from browser or postman and you should see a success a message: 
{
   "Api Status":"API IS UP AND RUNNING"
}

