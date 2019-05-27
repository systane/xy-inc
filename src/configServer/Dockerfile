FROM openjdk:8u121-alpine

COPY configServer/target/configServer-0.0.1-SNAPSHOT.jar /app/

RUN apk add --update \
    curl \
    && rm -rf /var/cache/apk/*

CMD ["java", "-jar", "/app/configServer-0.0.1-SNAPSHOT.jar"]