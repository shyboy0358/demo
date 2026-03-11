package com.example.demo.controller;

import com.example.demo.dto.ApiResult;
import com.example.demo.entity.OperationLog;
import com.example.demo.service.OperationLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class OperationLogController {

    private final OperationLogService operationLogService;

    public OperationLogController(OperationLogService operationLogService) {
        this.operationLogService = operationLogService;
    }

    @GetMapping
    public ApiResult<List<OperationLog>> getAllLogs() {
        return ApiResult.success(operationLogService.getAllLogs());
    }

    @GetMapping("/target/{targetType}")
    public ApiResult<List<OperationLog>> getLogsByTargetType(@PathVariable String targetType) {
        return ApiResult.success(operationLogService.getLogsByTargetType(targetType));
    }
}
