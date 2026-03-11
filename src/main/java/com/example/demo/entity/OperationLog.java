package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

public class OperationLog implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String operator;
    private String operationType;
    private String targetType;
    private Long targetId;
    private String content;
    private String ip;
    private Date createTime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOperator() { return operator; }
    public void setOperator(String operator) { this.operator = operator; }

    public String getOperationType() { return operationType; }
    public void setOperationType(String operationType) { this.operationType = operationType; }

    public String getTargetType() { return targetType; }
    public void setTargetType(String targetType) { this.targetType = targetType; }

    public Long getTargetId() { return targetId; }
    public void setTargetId(Long targetId) { this.targetId = targetId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getIp() { return ip; }
    public void setIp(String ip) { this.ip = ip; }

    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
}
