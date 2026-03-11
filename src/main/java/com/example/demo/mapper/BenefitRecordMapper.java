package com.example.demo.mapper;

import com.example.demo.entity.BenefitRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BenefitRecordMapper {

    List<BenefitRecord> findByMemberId(@Param("memberId") Long memberId);

    List<BenefitRecord> findByFamilyId(@Param("familyId") Long familyId);

    BenefitRecord findById(@Param("id") Long id);

    BenefitRecord findByBenefitIdAndMemberId(@Param("benefitId") Long benefitId,
                                              @Param("memberId") Long memberId);

    int insert(BenefitRecord record);

    int update(BenefitRecord record);
}
