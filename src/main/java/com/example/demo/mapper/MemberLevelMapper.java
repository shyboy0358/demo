package com.example.demo.mapper;

import com.example.demo.entity.MemberLevel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberLevelMapper {

    List<MemberLevel> findAll();

    MemberLevel findById(@Param("id") Long id);

    MemberLevel findByLevelCode(@Param("levelCode") String levelCode);

    MemberLevel findByPoints(@Param("points") Integer points);

    MemberLevel findNextLevel(@Param("currentSort") Integer currentSort);

    int insert(MemberLevel level);

    int update(MemberLevel level);

    int deleteById(@Param("id") Long id);
}
