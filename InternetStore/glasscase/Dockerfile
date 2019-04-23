FROM jboss/wildfly:15.0.1.Final

USER root

COPY wildfly/ /opt/jboss/wildfly/

ADD target/glasscase.war /opt/jboss/wildfly/standalone/deployments/

EXPOSE 8080 9990 61616

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
