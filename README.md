# Application

# Development Notes

## Commands

testing: `./mvnw -Dspring.profiles.active=test test`
development server: `./mvnw spring-boot:run -Dspring-boot.run.profiles=dev`

### prod

#### Release

Test with test environment before packaging.

```
#/bin/bash
./mvnw -Dspring.profiles.active=test package
```

#### Configuration

Template for configuration:
`target/classes/application-prod-template.properties`
