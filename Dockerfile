FROM amazoncorretto:17
ADD ./target/m3-common-service-1.0-SNAPSHOT.jar .
ENTRYPOINT ["java", "-Xmx30m", "-Xms10m", "-Xss200k", "-jar" , "m3-common-service-1.0-SNAPSHOT.jar"]