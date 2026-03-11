package com.example.demo.service.impl;

import com.example.demo.dto.MemberLevelDetailDTO;
import com.example.demo.entity.MemberBenefit;
import com.example.demo.entity.MemberLevel;
import com.example.demo.mapper.MemberBenefitMapper;
import com.example.demo.mapper.MemberLevelMapper;
import com.example.demo.service.MemberLevelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberLevelServiceImpl implements MemberLevelService {

    private final MemberLevelMapper levelMapper;
    private final MemberBenefitMapper benefitMapper;

    public MemberLevelServiceImpl(MemberLevelMapper levelMapper, MemberBenefitMapper benefitMapper) {
        this.levelMapper = levelMapper;
        this.benefitMapper = benefitMapper;
    }

    @Override
    public List<MemberLevel> getAllLevels() {
        return levelMapper.findAll();
    }

    @Override
    public MemberLevel getLevelById(Long id) {
        MemberLevel level = levelMapper.findById(id);
        if (level == null) {
            throw new RuntimeException("会员等级不存在: " + id);
        }
        return level;
    }

    @Override
    public MemberLevelDetailDTO getLevelDetail(Long id) {
        MemberLevel level = getLevelById(id);
        List<MemberBenefit> benefits = benefitMapper.findByLevelId(id);
        return new MemberLevelDetailDTO(level, benefits);
    }

    @Override
    public MemberLevel getLevelByPoints(Integer points) {
        MemberLevel level = levelMapper.findByPoints(points);
        if (level == null) {
            throw new RuntimeException("未找到匹配的会员等级, 积分: " + points);
        }
        return level;
    }

    @Override
    @Transactional
    public MemberLevel createLevel(MemberLevel level) {
        if (level.getStatus() == null) {
            level.setStatus(1);
        }
        levelMapper.insert(level);
        return levelMapper.findById(level.getId());
    }

    @Override
    @Transactional
    public MemberLevel updateLevel(MemberLevel level) {
        getLevelById(level.getId());
        levelMapper.update(level);
        return levelMapper.findById(level.getId());
    }

    @Override
    @Transactional
    public void deleteLevel(Long id) {
        getLevelById(id);
        benefitMapper.deleteByLevelId(id);
        levelMapper.deleteById(id);
    }
}
