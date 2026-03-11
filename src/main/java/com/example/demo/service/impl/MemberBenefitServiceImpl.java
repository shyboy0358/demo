package com.example.demo.service.impl;

import com.example.demo.entity.MemberBenefit;
import com.example.demo.entity.OperationLog;
import com.example.demo.mapper.MemberBenefitMapper;
import com.example.demo.mapper.OperationLogMapper;
import com.example.demo.service.MemberBenefitService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberBenefitServiceImpl implements MemberBenefitService {

    private final MemberBenefitMapper benefitMapper;
    private final OperationLogMapper operationLogMapper;

    public MemberBenefitServiceImpl(MemberBenefitMapper benefitMapper,
                                    OperationLogMapper operationLogMapper) {
        this.benefitMapper = benefitMapper;
        this.operationLogMapper = operationLogMapper;
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

    @Override
    public List<MemberBenefit> queryBenefits(String benefitName, String shelfStatus, String benefitCategory) {
        return benefitMapper.findByQuery(benefitName, shelfStatus, benefitCategory);
    }

    @Override
    @Transactional
    public MemberBenefit updateShelfStatus(Long id, String shelfStatus) {
        MemberBenefit benefit = getBenefitById(id);
        benefitMapper.updateShelfStatus(id, shelfStatus);

        OperationLog log = new OperationLog();
        log.setOperator("admin");
        log.setOperationType("SHELF_" + shelfStatus);
        log.setTargetType("BENEFIT");
        log.setTargetId(id);
        log.setContent("更新权益上下架状态: " + benefit.getBenefitName() + " -> " + shelfStatus);
        log.setIp("127.0.0.1");
        operationLogMapper.insert(log);

        return benefitMapper.findById(id);
    }
}
