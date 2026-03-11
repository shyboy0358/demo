package com.example.demo.service;

import com.example.demo.dto.MemberLevelDetailDTO;
import com.example.demo.entity.MemberLevel;

import java.util.List;

public interface MemberLevelService {

    List<MemberLevel> getAllLevels();

    MemberLevel getLevelById(Long id);

    MemberLevelDetailDTO getLevelDetail(Long id);

    MemberLevel getLevelByPoints(Integer points);

    MemberLevel createLevel(MemberLevel level);

    MemberLevel updateLevel(MemberLevel level);

    void deleteLevel(Long id);
}
