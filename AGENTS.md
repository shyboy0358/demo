# AGENTS.md

## Cursor Cloud specific instructions

### Project overview
This is a Spring Boot 2.1.6 demo/scaffold app (Java 8) using Maven. Dependencies: Spring Web, Spring Security, Spring Data Redis, MyBatis, Spring Actuator, Spring DevTools.

### System dependencies
- **Java 8** (`JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64`). The VM's default is Java 21; Java 8 must be selected via `update-alternatives` or `JAVA_HOME`. Already persisted in `~/.bashrc`.
- **Redis** must be running on localhost:6379 (default). Start with `sudo redis-server --daemonize yes`.

### DataSource caveat
The `pom.xml` includes `mybatis-spring-boot-starter` but no JDBC driver or database config exists. To run the app or tests without a database, **exclude** the DataSource auto-configuration:
```
export SPRING_AUTOCONFIGURE_EXCLUDE=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration,org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration
```
Set this env var before any `./mvnw` command (test, spring-boot:run, etc.).

### Common commands
| Task | Command |
|------|---------|
| Compile | `./mvnw compile` |
| Test | `./mvnw test` |
| Package | `./mvnw package` |
| Run (dev) | `./mvnw spring-boot:run` |

All commands require `JAVA_HOME` and `SPRING_AUTOCONFIGURE_EXCLUDE` set as described above.

### Spring Security
The app uses default Spring Security. On startup a random password is generated (look for `Using generated security password:` in logs). Default username is `user`.

### Ports
- Application: `8080`
- LiveReload (devtools): `35729`
