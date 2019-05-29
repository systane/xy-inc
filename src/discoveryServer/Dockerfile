FROM maven:3.5-jdk-8

COPY src/discoveryServer/src /app/discoveryServer/src

COPY src/discoveryServer/pom.xml /app/discoveryServer/

RUN mkdir /app/jar && \
mvn -f /app/discoveryServer/pom.xml package -DskipTests && \
mv /app/discoveryServer/target/discoveryServer-0.0.1-SNAPSHOT.jar /app/jar/discoveryServer.jar && \
rm -rf /app/discoveryServer/

CMD ["java", "-jar", "/app/jar/discoveryServer.jar"]