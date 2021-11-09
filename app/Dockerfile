FROM openjdk:11
COPY ./bin/stack-crud*.jar /usr/src/app.jar
WORKDIR /usr/src/
EXPOSE 8500
CMD ["java", "-Dspring.profiles.active=docker","-jar", "app.jar"]