package com.example.demo.service;

import com.example.demo.entity.OperationLog;

import java.util.List;

public interface OperationLogService {

    List<OperationLog> getAllLogs();

    List<OperationLog> getLogsByTargetType(String targetType);

    void log(String operator, String operationType, String targetType, Long targetId, String content, String ip);
}
