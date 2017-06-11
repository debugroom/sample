# Dockerfile for cassandra

FROM docker.io/centos:latest
MAINTAINER debugroom

RUN yum install -y \
       java-1.8.0-openjdk \
       java-1.8.0-openjdk-devel \
       wget tar

ENV JAVA_HOME /etc/alternatives/java_sdk
ADD datastax.repo /etc/yum.repos.d/
RUN yum -y install dsc30
RUN yum -y install cassandra30-tools
RUN sed -i s/\#-Xms4G/-Xms512M/g /etc/cassandra/conf/jvm.options
RUN sed -i s/\#-Xmx4G/-Xmx1G/g /etc/cassandra/conf/jvm.options
RUN systemctl enable cassandra

EXPOSE 9042
