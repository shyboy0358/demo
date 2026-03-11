# AGENTS.md

## Cursor Cloud specific instructions

### 项目概述

积分商城后台系统 — Spring Boot 2.1.6 / Java 8 / Maven / MyBatis / H2 / Redis / Spring Security。
当前已实现: 会员等级体系（5级）、会员权益管理、积分获取/消费/升级、积分历史记录。

### 系统依赖

- **JDK 8** (`openjdk-8-jdk`): `JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64`
- **Redis**: `sudo service redis-server start`（连接 `localhost:6379`）
- **Node.js**: 已安装，用于钉钉文档 MCP

### 常用命令

| 操作 | 命令 |
|------|------|
| 编译 | `JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 ./mvnw compile` |
| 测试 | `JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 ./mvnw test` |
| 运行 | `JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 ./mvnw spring-boot:run` |
| 打包 | `JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 ./mvnw package -DskipTests` |

应用启动在 **8080** 端口，`/api/**` 无需认证，H2 控制台 `/h2-console`。

### 注意事项

- **H2 内存数据库**: 重启后数据重置；`schema.sql` + `data.sql` 自动初始化。
- **无独立 lint**: Maven 编译 (`./mvnw compile`) 作为替代。
- **DevTools**: `spring-boot:run` 时自动启用热重载 (LiveReload port 35729)。

---

## 全流程开发工作流

Cloud Agent 接到需求时，必须按以下流程执行。每个阶段有对应的 skill 文件提供详细指导。

### Phase 1: 需求解析

**Skill 文件**: `.cursor/skills/parse-requirements.md`

1. 如果用户提供了钉钉文档链接，使用 `mcp-dingtalk-doc` 解析文档内容
2. 提取并整理需求要点: 功能列表、数据模型、业务规则、API 设计
3. 输出结构化的需求摘要，向用户确认理解无误后再动手编码

### Phase 2: 代码编写

**Skill 文件**: `.cursor/skills/code-implementation.md`

1. 按分层架构顺序编写: Entity → Enum → Mapper → Service → DTO → Controller
2. 更新 `schema.sql` / `data.sql` / `SecurityConfig`
3. 遵循 `.cursorrules` 中的代码规范

### Phase 3: 测试

**Skill 文件**: `.cursor/skills/test-and-fix.md`

1. 编写 Service 层 + Controller 层测试
2. 执行 `./mvnw test`，确保全量通过
3. 启动应用并用 curl 验证 API 端到端

### Phase 4: 修复

1. 如果测试失败，分析错误日志，定位根因
2. 修复代码后重新执行全量测试
3. 循环直到所有测试通过

### Phase 5: 提交

1. `git add -A && git status` 检查变更
2. `git commit -m "类型: 中文描述"` 提交
3. `git push` 推送

---

## 钉钉文档 MCP 使用说明

已安装 `mcp-dingtalk-doc`，Cookie 文件位于 `dingtalk_cookies.json`（工作区根目录）。

**直接调用方式**（Cloud Agent 环境）:
```bash
cd /workspace && node --input-type=module -e "
import { getCompleteDocumentData } from '$(npm root -g)/mcp-dingtalk-doc/dist/document-parser.js';
import fs from 'fs';
const cookie = JSON.parse(fs.readFileSync('dingtalk_cookies.json','utf-8')).cookie_string;
const result = await getCompleteDocumentData('文档URL', cookie, false);
fs.writeFileSync('/tmp/doc.html', result.html, 'utf-8');
console.log(result.html);
"
```

如果 Cookie 过期，运行 `cd $(npm root -g)/mcp-dingtalk-doc && node dist/cookie-manager.js --login` 重新登录。
