package com.example.demo.controller;

import com.example.demo.dto.ApiResult;
import com.example.demo.dto.FamilyGroupDetailDTO;
import com.example.demo.entity.FamilyGroup;
import com.example.demo.entity.FamilyMember;
import com.example.demo.service.FamilyGroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/families")
public class FamilyGroupController {

    private final FamilyGroupService familyGroupService;

    public FamilyGroupController(FamilyGroupService familyGroupService) {
        this.familyGroupService = familyGroupService;
    }

    @GetMapping
    public ApiResult<List<FamilyGroup>> queryFamilyGroups(
            @RequestParam(required = false) String familyName,
            @RequestParam(required = false) String ownerName,
            @RequestParam(required = false) String ownerPhone) {
        return ApiResult.success(familyGroupService.queryFamilyGroups(familyName, ownerName, ownerPhone));
    }

    @GetMapping("/{id}")
    public ApiResult<FamilyGroup> getFamilyGroupById(@PathVariable Long id) {
        return ApiResult.success(familyGroupService.getFamilyGroupById(id));
    }

    @GetMapping("/{id}/detail")
    public ApiResult<FamilyGroupDetailDTO> getFamilyGroupDetail(@PathVariable Long id) {
        return ApiResult.success(familyGroupService.getFamilyGroupDetail(id));
    }

    @PostMapping
    public ApiResult<FamilyGroup> createFamilyGroup(@RequestBody FamilyGroup group) {
        return ApiResult.success("创建成功", familyGroupService.createFamilyGroup(group));
    }

    @PostMapping("/{familyId}/members")
    public ApiResult<FamilyMember> addFamilyMember(@PathVariable Long familyId,
                                                    @RequestBody FamilyMember member) {
        member.setFamilyId(familyId);
        return ApiResult.success("添加成功", familyGroupService.addFamilyMember(member));
    }

    @DeleteMapping("/{familyId}/members/{memberId}")
    public ApiResult<Void> removeFamilyMember(@PathVariable Long familyId,
                                               @PathVariable Long memberId) {
        familyGroupService.removeFamilyMember(familyId, memberId);
        return ApiResult.success("移除成功", null);
    }
}
