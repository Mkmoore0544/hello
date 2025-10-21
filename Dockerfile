FROM eclipse-temurin:21-jre
WORKDIR /app
COPY target/hello-0.0.1-SNAPSHOT.jar /app/app.jar
ENV SERVER_PORT=8080
ENV JAVA_TOOL_OPTIONS="-XX:MaxRAMPercentage=75.0"
EXPOSE 8080
CMD ["java","-jar","/app/app.jar"]
