# Skill: 需求解析

## 触发场景
当用户提供钉钉文档链接或描述一个新功能需求时使用此 skill。

## 步骤

### 1. 获取需求文档

如果用户提供了钉钉文档链接（`alidocs.dingtalk.com`），使用以下命令解析:

```bash
cd /workspace && node --input-type=module -e "
import { getCompleteDocumentData } from '$(npm root -g)/mcp-dingtalk-doc/dist/document-parser.js';
import fs from 'fs';
const cookie = JSON.parse(fs.readFileSync('dingtalk_cookies.json','utf-8')).cookie_string;
const result = await getCompleteDocumentData('替换为文档URL', cookie, false);
if (result.html) {
  fs.writeFileSync('/tmp/requirement_doc.html', result.html, 'utf-8');
  console.log('文档标题:', result.document_data?.title || 'N/A');
}
if (result.document_data) {
  fs.writeFileSync('/tmp/requirement_raw.json', JSON.stringify(result.document_data, null, 2), 'utf-8');
}
console.log('解析完成');
"
```

然后读取 `/tmp/requirement_doc.html` 获取格式化内容。

### 2. 结构化分析

从需求文档中提取以下信息，输出为结构化摘要:

```markdown
## 需求摘要

### 功能模块
- 模块1: 简要描述
- 模块2: 简要描述

### 数据模型
| 表名 | 核心字段 | 说明 |
|------|----------|------|

### 业务规则
1. 规则描述...

### API 设计
| Method | Path | 说明 |
|--------|------|------|

### 与现有模块的关联
- 需要关联的表/接口...

### 不确定项 / 需确认项
- 需要和用户确认的问题...
```

### 3. 确认理解

将需求摘要展示给用户，等待确认后再进入编码阶段。如果用户说"直接开始"或类似表述，则跳过确认直接编码。

### 4. Cookie 过期处理

如果解析文档时报错 Cookie 失效:
1. 运行 `cd $(npm root -g)/mcp-dingtalk-doc && node dist/cookie-manager.js --login`
2. 在桌面浏览器完成扫码登录
3. 关闭浏览器窗口
4. 将新 Cookie 文件拷贝到工作区: `cp $(npm root -g)/mcp-dingtalk-doc/dingtalk_cookies.json /workspace/`
5. 重新解析文档
