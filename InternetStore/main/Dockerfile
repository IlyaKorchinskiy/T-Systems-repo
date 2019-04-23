FROM tomcat:latest

ENV JPDA_ADDRESS="8000"
ENV JPDA_TRANSPORT="dt_socket"

ADD target/main-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/store.war

WORKDIR /usr/local/tomcat

EXPOSE 8080
