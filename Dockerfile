FROM openjdk:8
MAINTAINER DEV

RUN cd app

COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

WORKDIR /app
