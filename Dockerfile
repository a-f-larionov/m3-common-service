FROM amazoncorretto:17
ADD ./build/libs/m3-common-service-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar" , "m3-common-service-0.0.1-SNAPSHOT.jar"]