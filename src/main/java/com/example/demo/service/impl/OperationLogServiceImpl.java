package com.example.demo.service.impl;

import com.example.demo.entity.OperationLog;
import com.example.demo.mapper.OperationLogMapper;
import com.example.demo.service.OperationLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OperationLogServiceImpl implements OperationLogService {

    private final OperationLogMapper operationLogMapper;

    public OperationLogServiceImpl(OperationLogMapper operationLogMapper) {
        this.operationLogMapper = operationLogMapper;
    }

    @Override
    public List<OperationLog> getAllLogs() {
        return operationLogMapper.findAll();
    }

    @Override
    public List<OperationLog> getLogsByTargetType(String targetType) {
        return operationLogMapper.findByTargetType(targetType);
    }

    @Override
    @Transactional
    public void log(String operator, String operationType, String targetType, Long targetId, String content, String ip) {
        OperationLog log = new OperationLog();
        log.setOperator(operator);
        log.setOperationType(operationType);
        log.setTargetType(targetType);
        log.setTargetId(targetId);
        log.setContent(content);
        log.setIp(ip);
        operationLogMapper.insert(log);
    }
}
