## Cursor Cloud specific instructions

Spring Boot 2.1.6 / Java 8 project using Maven wrapper (`./mvnw`). Uses H2 in-memory database — no external services needed.

- **Build**: `./mvnw compile`
- **Test**: `./mvnw test` (24 tests, all pass)
- **Run**: `./mvnw spring-boot:run`
- **No Lombok** — all entities use hand-written getters/setters in single-line format.
- Enums follow a consistent pattern: Chinese `description` field with constructor and `getDescription()`.
- Schema is in `src/main/resources/schema.sql`; seed data in `src/main/resources/data.sql`. Both run automatically on startup via H2.
