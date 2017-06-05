# Dockerfiel for Apache HTTP Server

FROM docker.io/centos:latest
MAINTAINER debugroom

RUN yum -y install httpd; yum clean all; systemctl enable httpd.service
Add index.html /var/www/html/index.html

EXPOSE 80

CMD ["/usr/sbin/init"]
