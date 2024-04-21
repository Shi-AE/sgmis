FROM oraclelinux:9-slim

LABEL auth=A.E.

RUN set -eux; \
	microdnf install \
		gzip \
		tar \
		\
		binutils \
		freetype fontconfig \
	; \
	microdnf clean all

ADD jdk-21_linux-x64_bin.tar.gz /usr/local/java/

ENV JAVA_HOME=/usr/local/java/jdk-21.0.2
ENV JRE_HOME=$JAVA_HOME/jre
ENV PATH=$JAVA_HOME/bin:$PATH
ENV CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
ENV LANG C.UTF-8