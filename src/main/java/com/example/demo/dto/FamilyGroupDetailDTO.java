package com.example.demo.dto;

import com.example.demo.entity.FamilyGroup;
import com.example.demo.entity.FamilyMember;

import java.io.Serializable;
import java.util.List;

public class FamilyGroupDetailDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private FamilyGroup familyGroup;
    private List<FamilyMember> members;

    public FamilyGroupDetailDTO() {}

    public FamilyGroupDetailDTO(FamilyGroup familyGroup, List<FamilyMember> members) {
        this.familyGroup = familyGroup;
        this.members = members;
    }

    public FamilyGroup getFamilyGroup() { return familyGroup; }
    public void setFamilyGroup(FamilyGroup familyGroup) { this.familyGroup = familyGroup; }

    public List<FamilyMember> getMembers() { return members; }
    public void setMembers(List<FamilyMember> members) { this.members = members; }
}
