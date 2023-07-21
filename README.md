# Application

# Development Notes

## Commands

testing: `./mvnw -Dspring.profiles.active=test test`
development server: `./mvnw spring-boot:run -Dspring-boot.run.profiles=dev`

### prod

#### Release

Skip test during packaging.

```
#/bin/bash
./mvnw -Dmaven.test.skip=true package
```

#### Configuration

Template for configuration:
`target/classes/application-prod-template.properties`
