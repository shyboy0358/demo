# Skill: 测试与修复

## 触发场景
代码编写完成后，进入测试验证阶段时使用此 skill。

## 环境准备

```bash
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
sudo service redis-server start 2>/dev/null || true
```

## 测试步骤

### Step 1: 编写自动化测试

#### Service 层测试

```java
package com.example.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class XxxServiceTest {

    @Autowired
    private XxxService xxxService;

    @Test
    public void testNormalCase() {
        // 正常流程测试
    }

    @Test(expected = RuntimeException.class)
    public void testErrorCase() {
        // 异常边界测试
    }
}
```

#### Controller 层测试

```java
package com.example.demo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class XxxControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetEndpoint() throws Exception {
        mockMvc.perform(get("/api/xxx"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));
    }

    @Test
    public void testPostEndpoint() throws Exception {
        String json = "{\"field\":\"value\"}";
        mockMvc.perform(post("/api/xxx")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }
}
```

**注意**: 所有 Controller 测试共享同一个 Spring 上下文和 H2 数据库。如果 testA 创建了数据，testB 能看到。断言数量时使用 `greaterThanOrEqualTo` 而非精确值。

### Step 2: 运行全量测试

```bash
JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 ./mvnw test 2>&1 | tail -30
```

期望结果: `BUILD SUCCESS`，`Failures: 0, Errors: 0`

### Step 3: 启动应用并 API 验证

```bash
# 先杀掉旧进程（如果有）
ps aux | grep "[s]pring-boot" | awk '{print $2}' | xargs -r kill 2>/dev/null
sleep 2

# 启动应用
JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 ./mvnw spring-boot:run &
sleep 12

# 验证新增 API
curl -s http://localhost:8080/api/新路径 | python3 -m json.tool
```

### Step 4: 完整的 API 演练

对每个新增/修改的 API 端点，执行 curl 测试:
- GET 查询列表
- GET 查询单条/详情
- POST 创建
- PUT 更新
- DELETE 删除
- 验证业务规则（如积分不足、重复注册等异常场景）

将 curl 输出保存为证据: `curl ... > /opt/cursor/artifacts/api_test_output.log`

## 修复流程

### 编译错误
1. 读取完整错误信息（不仅是最后几行）
2. 常见问题: import 缺失、类型不匹配、方法签名错误
3. 修复后重新编译: `./mvnw compile`

### 测试失败
1. 读取 surefire 报告: `cat target/surefire-reports/TEST-*.xml | head -100`
2. 根据异常类型定位:
   - `DataSourceBeanCreationException` → 检查 schema.sql 语法（H2 不支持 COMMENT）
   - `MyBatisSystemException` → 检查 Mapper XML 与接口参数是否匹配
   - `AssertionError` → 检查测试数据和断言逻辑
3. 修复后重新运行全量测试

### 运行时错误
1. 检查应用启动日志中的 ERROR/WARN
2. 常见问题:
   - `Table not found` → schema.sql 的 DROP/CREATE 顺序问题（外键依赖）
   - `Bean creation error` → 检查 @Mapper/@Service/@RestController 注解
   - `404` → 检查 Controller 路径和 SecurityConfig

## 测试通过后的清理

1. 确保没有临时调试代码残留
2. 应用保持运行（不要关闭），方便用户后续测试
3. 保存测试证据到 `/opt/cursor/artifacts/`
