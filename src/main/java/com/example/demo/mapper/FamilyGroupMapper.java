package com.example.demo.mapper;

import com.example.demo.entity.FamilyGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FamilyGroupMapper {

    List<FamilyGroup> findAll();

    List<FamilyGroup> findByQuery(@Param("familyName") String familyName,
                                  @Param("ownerName") String ownerName,
                                  @Param("ownerPhone") String ownerPhone);

    FamilyGroup findById(@Param("id") Long id);

    int insert(FamilyGroup group);

    int updateCurrentMembers(@Param("id") Long id, @Param("currentMembers") Integer currentMembers);
}
