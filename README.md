# Application

# Development Notes

## Configuration and Profiles

The default profile is disabled.

### Profiles

- Development: Starts web application in development mode.
- Test: For unit and integration test execution.
- Production: For deployment.

### Persistence Strategy

- Development: Connection pool will attempt to get available connection. Error is thrown if
  connection is not established after timeout.
- Test: Use H2 database.
- Production: Manual update via script. (Work in progress).

Template for configuration:
`target/classes/application-prod-template.properties`

## Commands

testing: `./mvnw -Dspring.profiles.active=test test`
development server: `./mvnw spring-boot:run -Dspring-boot.run.profiles=dev`

### production

#### Release

Test with test environment before packaging:

```
#/bin/bash
./mvnw -Dspring.profiles.active=test package
```

Package without test:

```
#/bin/bash
./mvnw -DskipTests=true package
```

#### useful commands

docker cp 192d4206273c:/schema.sql ./schema.sql
docker start 192d4206273c
docker exec -it 192d4206273c /bin/sh
docker run -d --name your-mysql-container-name -p 3306:3306 -e
MYSQL_ROOT_PASSWORD=your-root-password mysql:latest

#### drawio

[Setup](https://drive.google.com/file/d/1yd69Ey0j-RO6ZtIPW1xFuWCbe2JokwJt/view?usp=sharing)