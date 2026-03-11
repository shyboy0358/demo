package com.example.demo.service;

import com.example.demo.entity.MemberBenefit;

import java.util.List;

public interface MemberBenefitService {

    List<MemberBenefit> getBenefitsByLevelId(Long levelId);

    MemberBenefit getBenefitById(Long id);

    MemberBenefit createBenefit(MemberBenefit benefit);

    MemberBenefit updateBenefit(MemberBenefit benefit);

    void deleteBenefit(Long id);
}
