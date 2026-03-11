package com.example.demo.service;

import com.example.demo.dto.BenefitClaimDTO;
import com.example.demo.entity.BenefitRecord;

import java.util.List;

public interface BenefitRecordService {

    List<BenefitRecord> getRecordsByMemberId(Long memberId);

    List<BenefitRecord> getRecordsByFamilyId(Long familyId);

    BenefitRecord createRecord(BenefitRecord record);

    BenefitRecord claimBenefit(BenefitClaimDTO dto);

    BenefitRecord getRecordById(Long id);
}
