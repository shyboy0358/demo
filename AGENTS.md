# AGENTS.md

## Cursor Cloud specific instructions

### Project overview

This is a Spring Boot 2.1.6 demo application (Java 8, Maven). It includes starters for Web, Security, Redis, MyBatis, Actuator, and DevTools. There are no custom controllers active (the only controller has `@Controller` commented out) and `application.properties` is empty.

### System dependencies

- **JDK 8** (`openjdk-8-jdk`): Required; `pom.xml` sets `<java.version>1.8</java.version>`. Must set `JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64`.
- **Redis**: Required; `spring-boot-starter-data-redis` auto-configures a connection to `localhost:6379`. Start with `sudo service redis-server start`.

### Building and testing

- Build: `JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 ./mvnw compile`
- Test: `JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 ./mvnw test -Dspring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration`
- Package: `JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 ./mvnw package -DskipTests`

### Running the application

```bash
JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 ./mvnw spring-boot:run \
  -Dspring-boot.run.arguments="--spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration"
```

The app starts on port **8080**. Spring Security generates a random password printed in the console log (look for `Using generated security password:`). Default username is `user`.

### Important caveats

- **DataSource exclusion required**: The `mybatis-spring-boot-starter` triggers DataSource auto-configuration, but no JDBC driver is declared in `pom.xml` and `application.properties` is empty. You must exclude `DataSourceAutoConfiguration` via the `-Dspring.autoconfigure.exclude` property (as shown above) for both tests and `spring-boot:run`. Without this, the app fails to start.
- **No lint tooling**: This project has no separate lint step; Maven compilation (`./mvnw compile`) is the closest equivalent.
- **DevTools hot reload**: The `spring-boot-devtools` dependency enables LiveReload on port 35729 when running via `spring-boot:run`.
