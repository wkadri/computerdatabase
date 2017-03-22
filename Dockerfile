FROM maven:3.3.9-jdk-8-alpine

COPY . /usr/src/app
#RUN mvn package -f  /usr/src/app/pom.xml 
#RUN mvn install -f  /usr/src/app/pom.xml 
