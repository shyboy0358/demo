package com.example.demo.service;

import com.example.demo.dto.FamilyGroupDetailDTO;
import com.example.demo.entity.FamilyGroup;
import com.example.demo.entity.FamilyMember;

import java.util.List;

public interface FamilyGroupService {

    List<FamilyGroup> getAllFamilyGroups();

    List<FamilyGroup> queryFamilyGroups(String familyName, String ownerName, String ownerPhone);

    FamilyGroup getFamilyGroupById(Long id);

    FamilyGroupDetailDTO getFamilyGroupDetail(Long id);

    FamilyGroup createFamilyGroup(FamilyGroup group);

    void removeFamilyMember(Long familyId, Long memberId);

    FamilyMember addFamilyMember(FamilyMember member);
}
