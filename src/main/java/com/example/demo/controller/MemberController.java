package com.example.demo.controller;

import com.example.demo.dto.ApiResult;
import com.example.demo.dto.MemberProfileDTO;
import com.example.demo.dto.PointsOperationDTO;
import com.example.demo.entity.Member;
import com.example.demo.entity.PointsRecord;
import com.example.demo.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public ApiResult<List<Member>> getAllMembers() {
        return ApiResult.success(memberService.getAllMembers());
    }

    @GetMapping("/{id}")
    public ApiResult<Member> getMemberById(@PathVariable Long id) {
        return ApiResult.success(memberService.getMemberById(id));
    }

    @GetMapping("/{id}/profile")
    public ApiResult<MemberProfileDTO> getMemberProfile(@PathVariable Long id) {
        return ApiResult.success(memberService.getMemberProfile(id));
    }

    @PostMapping("/register")
    public ApiResult<Member> register(@RequestBody Member member) {
        return ApiResult.success("注册成功", memberService.register(member));
    }

    @PostMapping("/points/earn")
    public ApiResult<Member> earnPoints(@RequestBody PointsOperationDTO dto) {
        return ApiResult.success("积分获取成功", memberService.earnPoints(dto));
    }

    @PostMapping("/points/spend")
    public ApiResult<Member> spendPoints(@RequestBody PointsOperationDTO dto) {
        return ApiResult.success("积分消费成功", memberService.spendPoints(dto));
    }

    @GetMapping("/{id}/points/history")
    public ApiResult<List<PointsRecord>> getPointsHistory(@PathVariable Long id) {
        return ApiResult.success(memberService.getPointsHistory(id));
    }
}
