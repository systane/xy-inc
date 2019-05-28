FROM maven:3.5-jdk-8

COPY src/api/src /app/api/src

COPY src/api/pom.xml /app/api/

RUN mkdir /app/jar && \
mvn -f /app/api/pom.xml clean package -DskipTests && \
mv /app/api/target/api-0.0.1-SNAPSHOT.jar /app/jar/api.jar && \
rm -rf /app/api/

CMD ["java", "-jar", "/app/jar/api.jar"]