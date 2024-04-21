FROM jdk:21.0.2

LABEL auth=A.E.

ENV LANG C.UTF-8

COPY app /app

WORKDIR app

ENTRYPOINT ["java", "-jar", "sgmis-1.0.jar"]