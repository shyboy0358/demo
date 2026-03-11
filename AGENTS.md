# AGENTS.md

## Cursor Cloud specific instructions

### Project overview

Spring Boot 2.1.6 积分商城 demo (Java 8, Maven). 包含会员等级和权益模块，使用 H2 内存数据库、MyBatis ORM、Spring Security、Redis 和 Actuator。

### System dependencies

- **JDK 8** (`openjdk-8-jdk`): Required; `pom.xml` sets `<java.version>1.8</java.version>`. Must set `JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64`.
- **Redis**: Required; `spring-boot-starter-data-redis` auto-configures a connection to `localhost:6379`. Start with `sudo service redis-server start`.

### Building and testing

- Build: `JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 ./mvnw compile`
- Test: `JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 ./mvnw test`
- Package: `JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 ./mvnw package -DskipTests`

### Running the application

```bash
JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 ./mvnw spring-boot:run
```

The app starts on port **8080**. API endpoints under `/api/**` are open (no auth required). H2 console is at `/h2-console`.

### Key API endpoints

- `GET /api/levels` — 查询所有会员等级
- `GET /api/levels/{id}/detail` — 等级详情（含权益列表）
- `GET /api/levels/match?points=N` — 按积分匹配等级
- `POST /api/members/register` — 注册新会员
- `GET /api/members/{id}/profile` — 会员资料（含等级、权益、升级距离）
- `POST /api/members/points/earn` — 获取积分（自动触发升级）
- `POST /api/members/points/spend` — 消费积分
- `GET /api/members/{id}/points/history` — 积分变动记录
- `GET/POST/PUT/DELETE /api/benefits/**` — 权益 CRUD

### Important caveats

- **H2 内存数据库**: 数据在重启后重置；`schema.sql` 和 `data.sql` 自动执行初始化。
- **No lint tooling**: 项目无独立 lint；Maven 编译 (`./mvnw compile`) 是最接近的检查。
- **DevTools hot reload**: `spring-boot-devtools` 在 `spring-boot:run` 时启用 LiveReload (port 35729)。
