# Application

## OpenAPI docs

`./openapi`

# Development Notes

## Configuration and Profiles

The default profile is disabled.

### Profiles

- Development: Starts web application in development mode.
- Test: For unit and integration test execution.
- Production: For deployment.

### Persistence Strategy

[See database repository for more information](https://github.com/noellimx/forager-database)

- Development: Supply connection string to database. Connection pool will attempt to get available
  connection. This application is configured not to crash when connection is unstable.
- Test: Use H2 database.

Template for configuration:
`target/classes/application-prod-template.properties`

## Commands

testing: `./mvnw -Dspring.profiles.active=test test`
development server: `./run-dev.sh`

### production

#### Packaging

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

or with container:

```docker build . -f test.Dockerfile```

#### Release

Refer `.github/workflows/publish-image.yaml` for hook to create GitHub release.

#### drawio

[Setup](https://drive.google.com/file/d/1yd69Ey0j-RO6ZtIPW1xFuWCbe2JokwJt/view?usp=sharing)

