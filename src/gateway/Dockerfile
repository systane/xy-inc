FROM maven:3.5-jdk-8

COPY src/gateway/src /app/gateway/src

COPY src/gateway/pom.xml /app/gateway/

RUN mkdir /app/jar && \
mvn -f /app/gateway/pom.xml package -DskipTests && \
mv /app/gateway/target/gateway-0.0.1-SNAPSHOT.jar /app/jar/gateway.jar && \
rm -rf /app/gateway/

CMD ["java", "-jar", "/app/jar/gateway.jar"]