# ProfessorCompilation

## Description

This is service for automatic work verification.

### Requirement
- [OpenJDK](https://openjdk.java.net) ≥ 11
- [Apache Maven](https://maven.apache.org/) ≥ 3.6.3
- [Spring](https://spring.io/) ≥ 2.7.3
- [PostgreSQL](https://www.postgresql.org/) ≥ 12.14
- [Docker](https://www.docker.com/) ≥ 23.0.5

## How to run

### 0. Create table psql

Create user and database in postgres. Just run this command
```shell
$ ./initDB.sh
```

### 1. Run project

You can use
```shell
$ mvn package
``` 
for package project and after
```shell
$ java -jar target/professor_compilation-0.0.1-SNAPSHOT.jar`
```

## Swagger
If project is running, you can see [documentation in swagger](http://localhost:8080/swagger-ui/index.html)