# Dockerfile for Spring boot app using embedded tomcat server

FROM docker.io/centos:latest
MAINTAINER debugroom

RUN yum install -y \
       java-1.8.0-openjdk \
       java-1.8.0-openjdk-devel \
       wget tar

RUN wget http://repos.fedorapeople.org/repos/dchen/apache-maven/epel-apache-maven.repo -O /etc/yum.repos.d/epel-apache-maven.repo
RUN sed -i s/\$releasever/6/g /etc/yum.repos.d/epel-apache-maven.repo
RUN yum install -y apache-maven
ENV JAVA_HOME /etc/alternatives/jre
ADD spring-boot-app /var/local/springbootapp
RUN mvn install -f /var/local/springbootapp/pom.xml 
ENV DBSERVER_APP_USERNAME=app
ENV DBSERVER_APP_PASSWORD=app

EXPOSE 8080

CMD java -jar -Dspring.profiles.active=production,jpa /var/local/springbootapp/target/spring-boot-app-1.0-SNAPSHOT.jar
