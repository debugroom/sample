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
ADD sample-spring-cassandra /var/local/sample-spring-cassandra
ADD sample-spring-boot-parent /var/local/sample-spring-boot-parent
RUN mvn install -f /var/local/sample-spring-cassandra/pom.xml 

EXPOSE 8080

CMD java -jar -Dspring.profiles.active=pattern1,cluster /var/local/sample-spring-cassandra/target/sample-spring-cassandra-1.0-SNAPSHOT.jar
