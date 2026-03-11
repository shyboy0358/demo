package com.example.demo.mapper;

import com.example.demo.entity.FamilyMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FamilyMemberMapper {

    List<FamilyMember> findByFamilyId(@Param("familyId") Long familyId);

    FamilyMember findById(@Param("id") Long id);

    int insert(FamilyMember member);

    int deleteById(@Param("id") Long id);
}
