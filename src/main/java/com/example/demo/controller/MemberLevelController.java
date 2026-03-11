package com.example.demo.controller;

import com.example.demo.dto.ApiResult;
import com.example.demo.dto.MemberLevelDetailDTO;
import com.example.demo.entity.MemberLevel;
import com.example.demo.service.MemberLevelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/levels")
public class MemberLevelController {

    private final MemberLevelService levelService;

    public MemberLevelController(MemberLevelService levelService) {
        this.levelService = levelService;
    }

    @GetMapping
    public ApiResult<List<MemberLevel>> getAllLevels() {
        return ApiResult.success(levelService.getAllLevels());
    }

    @GetMapping("/{id}")
    public ApiResult<MemberLevel> getLevelById(@PathVariable Long id) {
        return ApiResult.success(levelService.getLevelById(id));
    }

    @GetMapping("/{id}/detail")
    public ApiResult<MemberLevelDetailDTO> getLevelDetail(@PathVariable Long id) {
        return ApiResult.success(levelService.getLevelDetail(id));
    }

    @GetMapping("/match")
    public ApiResult<MemberLevel> getLevelByPoints(@RequestParam Integer points) {
        return ApiResult.success(levelService.getLevelByPoints(points));
    }

    @PostMapping
    public ApiResult<MemberLevel> createLevel(@RequestBody MemberLevel level) {
        return ApiResult.success("创建成功", levelService.createLevel(level));
    }

    @PutMapping("/{id}")
    public ApiResult<MemberLevel> updateLevel(@PathVariable Long id, @RequestBody MemberLevel level) {
        level.setId(id);
        return ApiResult.success("更新成功", levelService.updateLevel(level));
    }

    @DeleteMapping("/{id}")
    public ApiResult<Void> deleteLevel(@PathVariable Long id) {
        levelService.deleteLevel(id);
        return ApiResult.success("删除成功", null);
    }
}
