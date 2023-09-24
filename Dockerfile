FROM openjdk:17

ADD target/patient-portal-api.jar patient-portal-api.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","patient-portal-api.jar"]