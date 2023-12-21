FROM amazoncorretto:17
ARG VERSION
VOLUME /tmp
COPY build/libs/service-${VERSION}.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]