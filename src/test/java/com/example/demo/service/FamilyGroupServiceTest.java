package com.example.demo.service;

import com.example.demo.dto.FamilyGroupDetailDTO;
import com.example.demo.entity.FamilyGroup;
import com.example.demo.entity.FamilyMember;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FamilyGroupServiceTest {

    @Autowired
    private FamilyGroupService familyGroupService;

    @Test
    public void testGetAllFamilyGroups() {
        List<FamilyGroup> groups = familyGroupService.getAllFamilyGroups();
        assertNotNull(groups);
        assertThat(groups.size(), greaterThanOrEqualTo(2));
    }

    @Test
    public void testGetFamilyGroupDetail() {
        FamilyGroupDetailDTO detail = familyGroupService.getFamilyGroupDetail(1L);
        assertNotNull(detail);
        assertNotNull(detail.getFamilyGroup());
        assertEquals("张三的家庭", detail.getFamilyGroup().getFamilyName());
        assertNotNull(detail.getMembers());
        assertThat(detail.getMembers().size(), greaterThanOrEqualTo(3));
    }

    @Test
    public void testCreateFamilyGroup() {
        FamilyGroup group = new FamilyGroup();
        group.setFamilyName("测试家庭");
        group.setOwnerUserId(1L);
        group.setOwnerName("测试用户");
        group.setOwnerPhone("13900000000");

        FamilyGroup created = familyGroupService.createFamilyGroup(group);
        assertNotNull(created);
        assertNotNull(created.getId());
        assertEquals("测试家庭", created.getFamilyName());
        assertEquals(Integer.valueOf(1), created.getStatus());
    }

    @Test
    public void testAddFamilyMember() {
        FamilyGroupDetailDTO before = familyGroupService.getFamilyGroupDetail(1L);
        int beforeSize = before.getMembers().size();

        FamilyMember member = new FamilyMember();
        member.setFamilyId(1L);
        member.setMemberName("新成员");
        member.setRole("STUDENT");
        member.setStudentCode("STU_NEW");

        FamilyMember added = familyGroupService.addFamilyMember(member);
        assertNotNull(added);
        assertNotNull(added.getId());
        assertEquals("新成员", added.getMemberName());

        FamilyGroupDetailDTO after = familyGroupService.getFamilyGroupDetail(1L);
        assertEquals(beforeSize + 1, after.getMembers().size());
    }

    @Test
    public void testRemoveFamilyMember() {
        FamilyMember member = new FamilyMember();
        member.setFamilyId(1L);
        member.setMemberName("待移除成员");
        member.setRole("STUDENT");
        member.setStudentCode("STU_REMOVE");
        FamilyMember added = familyGroupService.addFamilyMember(member);
        assertNotNull(added.getId());

        FamilyGroupDetailDTO before = familyGroupService.getFamilyGroupDetail(1L);
        int beforeSize = before.getMembers().size();

        familyGroupService.removeFamilyMember(1L, added.getId());

        FamilyGroupDetailDTO after = familyGroupService.getFamilyGroupDetail(1L);
        assertEquals(beforeSize - 1, after.getMembers().size());
    }

    @Test(expected = RuntimeException.class)
    public void testRemoveFamilyMemberNotParent() {
        familyGroupService.removeFamilyMember(1L, 1L);
    }

    @Test
    public void testQueryFamilyGroups() {
        List<FamilyGroup> results = familyGroupService.queryFamilyGroups(null, "张三", null);
        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertEquals("张三", results.get(0).getOwnerName());
    }
}
