FROM amazoncorretto:17-alpine-jdk
COPY target/*.jar java-kameleoon-trial-task.jar
ENTRYPOINT ["java","-jar","/java-kameleoon-trial-task.jar"]