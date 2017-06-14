# Dockerfile for Spring Data Cassandra app using embedded tomcat server

FROM docker.io/centos:latest
MAINTAINER debugroom

RUN yum install -y \
       java-1.8.0-openjdk \
       java-1.8.0-openjdk-devel \
       wget

RUN wget http://repos.fedorapeople.org/repos/dchen/apache-maven/epel-apache-maven.repo -O /etc/yum.repos.d/epel-apache-maven.repo
RUN sed -i s/\$releasever/6/g /etc/yum.repos.d/epel-apache-maven.repo
RUN yum install -y apache-maven
ENV JAVA_HOME /etc/alternatives/jre
ADD src /var/local/spring-data-cassandra-app
ADD pom.xml /var/local/spring-data-cassandra-app
RUN mvn install -f /var/local/spring-data-cassandra-app/pom.xml 

EXPOSE 8080

# CMD java -jar -Dspring.profiles.active=pattern1, cluster /var/local/springbootapp/target/sample-spring-data-cassandra-1.0-SNAPSHOT.jar
