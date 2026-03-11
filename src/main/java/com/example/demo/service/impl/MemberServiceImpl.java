package com.example.demo.service.impl;

import com.example.demo.dto.MemberProfileDTO;
import com.example.demo.dto.PointsOperationDTO;
import com.example.demo.entity.Member;
import com.example.demo.entity.MemberBenefit;
import com.example.demo.entity.MemberLevel;
import com.example.demo.entity.PointsRecord;
import com.example.demo.enums.PointsType;
import com.example.demo.mapper.MemberBenefitMapper;
import com.example.demo.mapper.MemberLevelMapper;
import com.example.demo.mapper.MemberMapper;
import com.example.demo.mapper.PointsRecordMapper;
import com.example.demo.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);

    private final MemberMapper memberMapper;
    private final MemberLevelMapper levelMapper;
    private final MemberBenefitMapper benefitMapper;
    private final PointsRecordMapper recordMapper;

    public MemberServiceImpl(MemberMapper memberMapper,
                             MemberLevelMapper levelMapper,
                             MemberBenefitMapper benefitMapper,
                             PointsRecordMapper recordMapper) {
        this.memberMapper = memberMapper;
        this.levelMapper = levelMapper;
        this.benefitMapper = benefitMapper;
        this.recordMapper = recordMapper;
    }

    @Override
    public List<Member> getAllMembers() {
        return memberMapper.findAll();
    }

    @Override
    public Member getMemberById(Long id) {
        Member member = memberMapper.findById(id);
        if (member == null) {
            throw new RuntimeException("会员不存在: " + id);
        }
        return member;
    }

    @Override
    public MemberProfileDTO getMemberProfile(Long id) {
        Member member = getMemberById(id);
        MemberLevel currentLevel = levelMapper.findById(member.getLevelId());
        MemberLevel nextLevel = null;
        Integer pointsToNext = null;

        if (currentLevel != null) {
            nextLevel = levelMapper.findNextLevel(currentLevel.getLevelSort());
            if (nextLevel != null) {
                pointsToNext = nextLevel.getMinPoints() - member.getTotalPoints();
                if (pointsToNext < 0) pointsToNext = 0;
            }
        }

        List<MemberBenefit> benefits = benefitMapper.findByLevelId(member.getLevelId());

        MemberProfileDTO dto = new MemberProfileDTO();
        dto.setMember(member);
        dto.setCurrentLevel(currentLevel);
        dto.setNextLevel(nextLevel);
        dto.setBenefits(benefits);
        dto.setPointsToNextLevel(pointsToNext);
        return dto;
    }

    @Override
    @Transactional
    public Member register(Member member) {
        if (memberMapper.findByUsername(member.getUsername()) != null) {
            throw new RuntimeException("用户名已存在: " + member.getUsername());
        }

        member.setTotalPoints(0);
        member.setAvailablePoints(0);

        MemberLevel initLevel = levelMapper.findByPoints(0);
        member.setLevelId(initLevel != null ? initLevel.getId() : 1L);

        memberMapper.insert(member);
        log.info("新会员注册: {}, 初始等级: {}", member.getUsername(),
                initLevel != null ? initLevel.getLevelName() : "N/A");

        return memberMapper.findById(member.getId());
    }

    @Override
    @Transactional
    public Member earnPoints(PointsOperationDTO dto) {
        Member member = getMemberById(dto.getMemberId());
        int newTotal = member.getTotalPoints() + dto.getPoints();
        int newAvailable = member.getAvailablePoints() + dto.getPoints();

        MemberLevel newLevel = levelMapper.findByPoints(newTotal);
        Long newLevelId = newLevel != null ? newLevel.getId() : member.getLevelId();

        boolean levelChanged = !newLevelId.equals(member.getLevelId());
        if (levelChanged) {
            log.info("会员 {} 升级: {} -> {}", member.getUsername(),
                    member.getLevelId(), newLevel.getLevelName());
        }

        memberMapper.updatePoints(member.getId(), newTotal, newAvailable, newLevelId);

        PointsRecord record = new PointsRecord();
        record.setMemberId(member.getId());
        record.setPoints(dto.getPoints());
        record.setType(dto.getType() != null ? dto.getType() : PointsType.EARN.name());
        record.setDescription(dto.getDescription() != null ? dto.getDescription() : "积分获取");
        recordMapper.insert(record);

        if (levelChanged) {
            PointsRecord levelUpRecord = new PointsRecord();
            levelUpRecord.setMemberId(member.getId());
            levelUpRecord.setPoints(0);
            levelUpRecord.setType(PointsType.LEVEL_UP_BONUS.name());
            levelUpRecord.setDescription("恭喜升级到 " + newLevel.getLevelName());
            recordMapper.insert(levelUpRecord);
        }

        return memberMapper.findById(member.getId());
    }

    @Override
    @Transactional
    public Member spendPoints(PointsOperationDTO dto) {
        Member member = getMemberById(dto.getMemberId());

        if (member.getAvailablePoints() < dto.getPoints()) {
            throw new RuntimeException("可用积分不足, 当前: " + member.getAvailablePoints()
                    + ", 需要: " + dto.getPoints());
        }

        int newAvailable = member.getAvailablePoints() - dto.getPoints();
        memberMapper.updatePoints(member.getId(), member.getTotalPoints(), newAvailable, member.getLevelId());

        PointsRecord record = new PointsRecord();
        record.setMemberId(member.getId());
        record.setPoints(-dto.getPoints());
        record.setType(PointsType.SPEND.name());
        record.setDescription(dto.getDescription() != null ? dto.getDescription() : "积分消费");
        recordMapper.insert(record);

        return memberMapper.findById(member.getId());
    }

    @Override
    public List<PointsRecord> getPointsHistory(Long memberId) {
        getMemberById(memberId);
        return recordMapper.findByMemberId(memberId);
    }
}
