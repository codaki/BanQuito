# Use the official Payara Server Full image with JDK 11
FROM payara/server-full:5.2021.10-jdk11

# Copy the WAR file to the autodeploy directory
COPY target/BANQUITO_SOAP_JAVA-1.war /opt/payara/appserver/glassfish/domains/domain1/autodeploy/

# Expose the default HTTP and admin ports
EXPOSE 8080 4848

# Start Payara Server and deploy the application
CMD ["/bin/sh", "-c", "/opt/payara/appserver/bin/asadmin start-domain -v && /opt/payara/appserver/bin/asadmin deploy /opt/payara/appserver/glassfish/domains/domain1/autodeploy/BANQUITO_SOAP_JAVA-1.war"]

# Health check to verify deployment
HEALTHCHECK --interval=30s --timeout=10s --start-period=30s --retries=3 \
    CMD curl -f http://localhost:8080/BANQUITO_SOAP_JAVA-1/ || exit 1