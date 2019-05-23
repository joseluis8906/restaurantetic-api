FROM openjdk:8u181-jdk-stretch

WORKDIR /app

RUN cd /tmp
RUN wget https://services.gradle.org/distributions/gradle-5.2.1-bin.zip
RUN mkdir /opt/gradle
RUN unzip gradle-5.2.1-bin.zip -d /opt/gradle/
RUN ln -s /opt/gradle/gradle-5.2.1/bin/gradle /usr/local/bin/gradle
