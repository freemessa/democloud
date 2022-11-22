FROM openjdk:8-jre-alpine
ARG profile
ENV SPRING_PROFILES_ACTIVE=${profile}
ADD target/webflux-0.0.1-SNAPSHOT.jar /webflux-0.0.1-SNAPSHOT.jar
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone
EXPOSE 18086
ENTRYPOINT ["java","-jar","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=18086","-Xms512m", "-Xmx512m", "-Xmn256m", "/zuul-0.0.1-SNAPSHOT.jar"]
MAINTAINER demo