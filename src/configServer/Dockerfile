FROM maven:3.5-jdk-8

COPY src/configServer/src /app/configServer/src

COPY src/configServer/pom.xml /app/configServer/

RUN mkdir /app/jar && \
mvn -f /app/configServer/pom.xml package -DskipTests && \
mv /app/configServer/target/configServer-0.0.1-SNAPSHOT.jar /app/jar/configServer.jar && \
rm -rf /app/configServer/

CMD ["java", "-jar", "/app/jar/configServer.jar"]