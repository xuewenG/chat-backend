FROM maven:3.8.6-openjdk-11 AS BUILDER
LABEL maintainer="xuewenG" \
    site="https://github.com/xuewenG/chat-backend"

ENV MY_HOME=/root
RUN mkdir -p $MY_HOME
WORKDIR $MY_HOME

ADD pom.xml $MY_HOME
RUN mvn dependency:go-offline
ADD . $MY_HOME
RUN mvn clean verify -DskipTests

FROM openjdk:11-jdk-stretch
ENV MY_HOME=/root
RUN mkdir -p $MY_HOME
WORKDIR $MY_HOME

COPY --from=BUILDER /root/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
