FROM openjdk:8u121-alpine

COPY api/target/order-service-0.0.1-SNAPSHOT.jar /app/

RUN apk add --update \
    curl \
    && rm -rf /var/cache/apk/*

CMD ["java", "-jar", "/app/order-service-0.0.1-SNAPSHOT.jar"]