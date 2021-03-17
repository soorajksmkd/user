FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY /target/user-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
EXPOSE 8000
ENV JAVA_OPTS=""
RUN sh -c "touch user-0.0.1-SNAPSHOT.jar"
ENTRYPOINT [ "java", "-jar", "user-0.0.1-SNAPSHOT.jar" ]