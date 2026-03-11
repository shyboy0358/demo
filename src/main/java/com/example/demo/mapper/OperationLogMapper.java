package com.example.demo.mapper;

import com.example.demo.entity.OperationLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OperationLogMapper {

    List<OperationLog> findAll();

    List<OperationLog> findByTargetType(@Param("targetType") String targetType);

    int insert(OperationLog log);
}
