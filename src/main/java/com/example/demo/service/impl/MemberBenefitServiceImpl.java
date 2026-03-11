package com.example.demo.service.impl;

import com.example.demo.entity.MemberBenefit;
import com.example.demo.mapper.MemberBenefitMapper;
import com.example.demo.service.MemberBenefitService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberBenefitServiceImpl implements MemberBenefitService {

    private final MemberBenefitMapper benefitMapper;

    public MemberBenefitServiceImpl(MemberBenefitMapper benefitMapper) {
        this.benefitMapper = benefitMapper;
    }

    @Override
    public List<MemberBenefit> getBenefitsByLevelId(Long levelId) {
        return benefitMapper.findByLevelId(levelId);
    }

    @Override
    public MemberBenefit getBenefitById(Long id) {
        MemberBenefit benefit = benefitMapper.findById(id);
        if (benefit == null) {
            throw new RuntimeException("会员权益不存在: " + id);
        }
        return benefit;
    }

    @Override
    @Transactional
    public MemberBenefit createBenefit(MemberBenefit benefit) {
        if (benefit.getStatus() == null) {
            benefit.setStatus(1);
        }
        benefitMapper.insert(benefit);
        return benefitMapper.findById(benefit.getId());
    }

    @Override
    @Transactional
    public MemberBenefit updateBenefit(MemberBenefit benefit) {
        getBenefitById(benefit.getId());
        benefitMapper.update(benefit);
        return benefitMapper.findById(benefit.getId());
    }

    @Override
    @Transactional
    public void deleteBenefit(Long id) {
        getBenefitById(id);
        benefitMapper.deleteById(id);
    }
}
