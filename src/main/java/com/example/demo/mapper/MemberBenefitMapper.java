package com.example.demo.mapper;

import com.example.demo.entity.MemberBenefit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberBenefitMapper {

    List<MemberBenefit> findByLevelId(@Param("levelId") Long levelId);

    List<MemberBenefit> findAll();

    MemberBenefit findById(@Param("id") Long id);

    int insert(MemberBenefit benefit);

    int update(MemberBenefit benefit);

    int deleteById(@Param("id") Long id);

    int deleteByLevelId(@Param("levelId") Long levelId);

    List<MemberBenefit> findByQuery(@Param("benefitName") String benefitName, @Param("shelfStatus") String shelfStatus, @Param("benefitCategory") String benefitCategory);

    int updateShelfStatus(@Param("id") Long id, @Param("shelfStatus") String shelfStatus);
}
