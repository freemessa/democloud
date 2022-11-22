FROM openjdk:8-jre-alpine
ARG profile
ENV SPRING_PROFILES_ACTIVE=${profile}
ADD target/fegin-0.0.1-SNAPSHOT.jar /fegin-0.0.1-SNAPSHOT.jar
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone
EXPOSE 8085
ENTRYPOINT ["java","-jar","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8085","-Xms512m", "-Xmx512m", "-Xmn256m", "/fegin-0.0.1-SNAPSHOT.jar"]
MAINTAINER demo