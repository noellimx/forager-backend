#!/bin/sh



export SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3307/app?createDatabaseIfNotExist=true
export SPRING_DATASOURCE_USERNAME=root
export SPRING_DATASOURCE_PASSWORD=your-root-password
export SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT=org.hibernate.dialect.MySQLDialect
#export SPRING_JPA_HIBERNATE_DDLAUTO=create-drop
#export SPRING_JPA_GENERATEDDL=true
#spring.jpa.generate-ddl
#spring.jpa.hibernate.ddl-auto=none

export SPRING_DATASOURCE_HIKARI_CONNECTIONTIMEOUT=3000
export SPRING_DATASOURCE_HIKARI_INITIALIZATIONFAILTIMEOUT=-1

./mvnw spring-boot:run -Dspring-boot.run.profiles=dev

java -jar ./target/forager-snapshot.jar
