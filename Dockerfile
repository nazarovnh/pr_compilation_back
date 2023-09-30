FROM maven:3.6.3-openjdk-11 as maven
WORKDIR /app
COPY . /app
RUN mvn package
RUN mvn spring-boot:run
