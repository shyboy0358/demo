package com.example.demo.service.impl;

import com.example.demo.dto.FamilyGroupDetailDTO;
import com.example.demo.entity.FamilyGroup;
import com.example.demo.entity.FamilyMember;
import com.example.demo.entity.OperationLog;
import com.example.demo.mapper.FamilyGroupMapper;
import com.example.demo.mapper.FamilyMemberMapper;
import com.example.demo.mapper.OperationLogMapper;
import com.example.demo.service.FamilyGroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FamilyGroupServiceImpl implements FamilyGroupService {

    private final FamilyGroupMapper familyGroupMapper;
    private final FamilyMemberMapper familyMemberMapper;
    private final OperationLogMapper operationLogMapper;

    public FamilyGroupServiceImpl(FamilyGroupMapper familyGroupMapper,
                                  FamilyMemberMapper familyMemberMapper,
                                  OperationLogMapper operationLogMapper) {
        this.familyGroupMapper = familyGroupMapper;
        this.familyMemberMapper = familyMemberMapper;
        this.operationLogMapper = operationLogMapper;
    }

    @Override
    public List<FamilyGroup> getAllFamilyGroups() {
        return familyGroupMapper.findAll();
    }

    @Override
    public List<FamilyGroup> queryFamilyGroups(String familyName, String ownerName, String ownerPhone) {
        return familyGroupMapper.findByQuery(familyName, ownerName, ownerPhone);
    }

    @Override
    public FamilyGroup getFamilyGroupById(Long id) {
        FamilyGroup group = familyGroupMapper.findById(id);
        if (group == null) {
            throw new RuntimeException("家庭组不存在: " + id);
        }
        return group;
    }

    @Override
    public FamilyGroupDetailDTO getFamilyGroupDetail(Long id) {
        FamilyGroup group = getFamilyGroupById(id);
        List<FamilyMember> members = familyMemberMapper.findByFamilyId(id);
        return new FamilyGroupDetailDTO(group, members);
    }

    @Override
    @Transactional
    public FamilyGroup createFamilyGroup(FamilyGroup group) {
        if (group.getStatus() == null) {
            group.setStatus(1);
        }
        familyGroupMapper.insert(group);
        return familyGroupMapper.findById(group.getId());
    }

    @Override
    @Transactional
    public void removeFamilyMember(Long familyId, Long memberId) {
        FamilyGroup group = getFamilyGroupById(familyId);
        FamilyMember member = familyMemberMapper.findById(memberId);
        if (member == null) {
            throw new RuntimeException("家庭成员不存在: " + memberId);
        }
        if (!familyId.equals(member.getFamilyId())) {
            throw new RuntimeException("该成员不属于此家庭组");
        }
        if ("PARENT".equals(member.getRole())) {
            throw new RuntimeException("不能移除家庭组创建者");
        }
        familyMemberMapper.deleteById(memberId);
        familyGroupMapper.updateCurrentMembers(familyId, group.getCurrentMembers() - 1);

        OperationLog log = new OperationLog();
        log.setOperator("system");
        log.setOperationType("REMOVE_MEMBER");
        log.setTargetType("FAMILY_MEMBER");
        log.setTargetId(memberId);
        log.setContent("从家庭组[" + group.getFamilyName() + "]移除成员[" + member.getMemberName() + "]");
        log.setIp("127.0.0.1");
        operationLogMapper.insert(log);
    }

    @Override
    @Transactional
    public FamilyMember addFamilyMember(FamilyMember member) {
        FamilyGroup group = getFamilyGroupById(member.getFamilyId());
        if (group.getCurrentMembers() >= group.getMaxMembers()) {
            throw new RuntimeException("家庭组成员已满，最大人数: " + group.getMaxMembers());
        }
        if (member.getStatus() == null) {
            member.setStatus(1);
        }
        familyMemberMapper.insert(member);
        familyGroupMapper.updateCurrentMembers(group.getId(), group.getCurrentMembers() + 1);
        return familyMemberMapper.findById(member.getId());
    }
}
