FROM openjdk:8u121-alpine

COPY discoveryServer/target/discoveryServer-0.0.1-SNAPSHOT.jar /app/

RUN apk add --update \
    curl \
    && rm -rf /var/cache/apk/*

CMD ["java", "-jar", "/app/discoveryServer-0.0.1-SNAPSHOT.jar"]