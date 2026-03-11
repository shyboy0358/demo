package com.example.demo.service.impl;

import com.example.demo.dto.BenefitClaimDTO;
import com.example.demo.entity.BenefitRecord;
import com.example.demo.entity.OperationLog;
import com.example.demo.mapper.BenefitRecordMapper;
import com.example.demo.mapper.OperationLogMapper;
import com.example.demo.service.BenefitRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class BenefitRecordServiceImpl implements BenefitRecordService {

    private final BenefitRecordMapper benefitRecordMapper;
    private final OperationLogMapper operationLogMapper;

    public BenefitRecordServiceImpl(BenefitRecordMapper benefitRecordMapper,
                                    OperationLogMapper operationLogMapper) {
        this.benefitRecordMapper = benefitRecordMapper;
        this.operationLogMapper = operationLogMapper;
    }

    @Override
    public List<BenefitRecord> getRecordsByMemberId(Long memberId) {
        return benefitRecordMapper.findByMemberId(memberId);
    }

    @Override
    public List<BenefitRecord> getRecordsByFamilyId(Long familyId) {
        return benefitRecordMapper.findByFamilyId(familyId);
    }

    @Override
    @Transactional
    public BenefitRecord createRecord(BenefitRecord record) {
        if (record.getStatus() == null) {
            record.setStatus("PENDING");
        }
        benefitRecordMapper.insert(record);
        return benefitRecordMapper.findById(record.getId());
    }

    @Override
    @Transactional
    public BenefitRecord claimBenefit(BenefitClaimDTO dto) {
        BenefitRecord record = benefitRecordMapper.findByBenefitIdAndMemberId(dto.getBenefitId(), dto.getMemberId());
        if (record == null) {
            record = new BenefitRecord();
            record.setBenefitId(dto.getBenefitId());
            record.setMemberId(dto.getMemberId());
            record.setFamilyId(dto.getFamilyId());
            record.setStudentCode(dto.getStudentCode());
            record.setGoodsId(dto.getGoodsId());
            record.setStatus("CLAIMED");
            record.setClaimTime(new Date());
            benefitRecordMapper.insert(record);
            record = benefitRecordMapper.findById(record.getId());
        } else {
            record.setStatus("CLAIMED");
            record.setClaimTime(new Date());
            record.setStudentCode(dto.getStudentCode());
            record.setGoodsId(dto.getGoodsId());
            benefitRecordMapper.update(record);
            record = benefitRecordMapper.findById(record.getId());
        }

        OperationLog log = new OperationLog();
        log.setOperator("system");
        log.setOperationType("CLAIM");
        log.setTargetType("BENEFIT_RECORD");
        log.setTargetId(record.getId());
        log.setContent("领取权益, benefitId=" + dto.getBenefitId() + ", memberId=" + dto.getMemberId());
        log.setIp("127.0.0.1");
        operationLogMapper.insert(log);

        return record;
    }

    @Override
    public BenefitRecord getRecordById(Long id) {
        BenefitRecord record = benefitRecordMapper.findById(id);
        if (record == null) {
            throw new RuntimeException("权益记录不存在: " + id);
        }
        return record;
    }
}
