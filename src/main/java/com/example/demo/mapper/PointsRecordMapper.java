package com.example.demo.mapper;

import com.example.demo.entity.PointsRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PointsRecordMapper {

    List<PointsRecord> findByMemberId(@Param("memberId") Long memberId);

    int insert(PointsRecord record);
}
