# create-business
Spring boot application used to create, retrieve, search, and update a business.

# Setup instructions
1. Download jdk 8 - https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
2. Download Maven - http://www.trieuvan.com/apache/maven/maven-3/3.6.1/binaries/apache-maven-3.6.1-bin.zip

# How to setup JDK and Maven on Windows Machine
Follow below steps
https://www.mkyong.com/maven/how-to-install-maven-in-windows/ 

Verify the install by running the below commands from command prompt

java --version
mvn -version

edited

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

# Running the application as a Docker image locally.

Install docker in your local machine using the below link.

https://docs.docker.com/docker-for-windows/install/

Building the Docker image commands.

Navigate to the Dockerfile location and run:

Docker build -t <IMAGE_NAME>:1.0 .

eg... Docker build -t mybusiness:1.0 .

After successful build run the command to find the image you built.

Docker images

Now run the image in your local.

Docker run -p 8080:8080 -t mybusiness:1.0

You hit your localhost using:

http://localhost:8080/<YOUR END POINT>

For more docker commands...

https://www.docker.com/sites/default/files/Docker_CheatSheet_08.09.2016_0.pdf

