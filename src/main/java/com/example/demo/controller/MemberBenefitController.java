package com.example.demo.controller;

import com.example.demo.dto.ApiResult;
import com.example.demo.entity.MemberBenefit;
import com.example.demo.service.MemberBenefitService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/benefits")
public class MemberBenefitController {

    private final MemberBenefitService benefitService;

    public MemberBenefitController(MemberBenefitService benefitService) {
        this.benefitService = benefitService;
    }

    @GetMapping("/level/{levelId}")
    public ApiResult<List<MemberBenefit>> getBenefitsByLevel(@PathVariable Long levelId) {
        return ApiResult.success(benefitService.getBenefitsByLevelId(levelId));
    }

    @GetMapping("/{id}")
    public ApiResult<MemberBenefit> getBenefitById(@PathVariable Long id) {
        return ApiResult.success(benefitService.getBenefitById(id));
    }

    @PostMapping
    public ApiResult<MemberBenefit> createBenefit(@RequestBody MemberBenefit benefit) {
        return ApiResult.success("创建成功", benefitService.createBenefit(benefit));
    }

    @PutMapping("/{id}")
    public ApiResult<MemberBenefit> updateBenefit(@PathVariable Long id, @RequestBody MemberBenefit benefit) {
        benefit.setId(id);
        return ApiResult.success("更新成功", benefitService.updateBenefit(benefit));
    }

    @DeleteMapping("/{id}")
    public ApiResult<Void> deleteBenefit(@PathVariable Long id) {
        benefitService.deleteBenefit(id);
        return ApiResult.success("删除成功", null);
    }

    @GetMapping("/manage")
    public ApiResult<List<MemberBenefit>> queryBenefits(
            @RequestParam(required = false) String benefitName,
            @RequestParam(required = false) String shelfStatus,
            @RequestParam(required = false) String benefitCategory) {
        return ApiResult.success(benefitService.queryBenefits(benefitName, shelfStatus, benefitCategory));
    }

    @PutMapping("/{id}/shelf")
    public ApiResult<MemberBenefit> updateShelfStatus(@PathVariable Long id,
                                                       @RequestParam String shelfStatus) {
        return ApiResult.success("更新成功", benefitService.updateShelfStatus(id, shelfStatus));
    }
}
