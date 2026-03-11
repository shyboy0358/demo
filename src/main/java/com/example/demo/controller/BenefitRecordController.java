package com.example.demo.controller;

import com.example.demo.dto.ApiResult;
import com.example.demo.dto.BenefitClaimDTO;
import com.example.demo.entity.BenefitRecord;
import com.example.demo.service.BenefitRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/benefit-records")
public class BenefitRecordController {

    private final BenefitRecordService benefitRecordService;

    public BenefitRecordController(BenefitRecordService benefitRecordService) {
        this.benefitRecordService = benefitRecordService;
    }

    @GetMapping("/member/{memberId}")
    public ApiResult<List<BenefitRecord>> getRecordsByMemberId(@PathVariable Long memberId) {
        return ApiResult.success(benefitRecordService.getRecordsByMemberId(memberId));
    }

    @GetMapping("/family/{familyId}")
    public ApiResult<List<BenefitRecord>> getRecordsByFamilyId(@PathVariable Long familyId) {
        return ApiResult.success(benefitRecordService.getRecordsByFamilyId(familyId));
    }

    @GetMapping("/{id}")
    public ApiResult<BenefitRecord> getRecordById(@PathVariable Long id) {
        return ApiResult.success(benefitRecordService.getRecordById(id));
    }

    @PostMapping
    public ApiResult<BenefitRecord> createRecord(@RequestBody BenefitRecord record) {
        return ApiResult.success("创建成功", benefitRecordService.createRecord(record));
    }

    @PostMapping("/claim")
    public ApiResult<BenefitRecord> claimBenefit(@RequestBody BenefitClaimDTO dto) {
        return ApiResult.success("领取成功", benefitRecordService.claimBenefit(dto));
    }
}
