package com.example.demo.service;

import com.example.demo.dto.MemberProfileDTO;
import com.example.demo.dto.PointsOperationDTO;
import com.example.demo.entity.Member;
import com.example.demo.entity.PointsRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    public void testGetMemberProfile() {
        MemberProfileDTO profile = memberService.getMemberProfile(1L);
        assertNotNull(profile);
        assertEquals("zhangsan", profile.getMember().getUsername());
        assertEquals("银卡会员", profile.getCurrentLevel().getLevelName());
        assertNotNull(profile.getNextLevel());
        assertEquals("金卡会员", profile.getNextLevel().getLevelName());
        assertNotNull(profile.getPointsToNextLevel());
        assertTrue(profile.getPointsToNextLevel() > 0);
    }

    @Test
    public void testRegisterMember() {
        Member member = new Member();
        member.setUsername("wangwu");
        member.setNickname("王五");
        member.setPhone("13800138003");
        member.setEmail("wangwu@example.com");

        Member registered = memberService.register(member);
        assertNotNull(registered);
        assertNotNull(registered.getId());
        assertEquals(Integer.valueOf(0), registered.getTotalPoints());
        assertEquals(Integer.valueOf(0), registered.getAvailablePoints());
        assertEquals(Long.valueOf(1L), registered.getLevelId());
    }

    @Test(expected = RuntimeException.class)
    public void testRegisterDuplicateUsername() {
        Member member = new Member();
        member.setUsername("zhangsan");
        memberService.register(member);
    }

    @Test
    public void testEarnPoints() {
        PointsOperationDTO dto = new PointsOperationDTO();
        dto.setMemberId(1L);
        dto.setPoints(500);
        dto.setDescription("测试积分获取");

        Member before = memberService.getMemberById(1L);
        int beforeTotal = before.getTotalPoints();

        Member after = memberService.earnPoints(dto);
        assertEquals(Integer.valueOf(beforeTotal + 500), after.getTotalPoints());
    }

    @Test
    public void testSpendPoints() {
        PointsOperationDTO dto = new PointsOperationDTO();
        dto.setMemberId(2L);
        dto.setPoints(100);
        dto.setDescription("测试积分消费");

        Member before = memberService.getMemberById(2L);
        int beforeAvailable = before.getAvailablePoints();

        Member after = memberService.spendPoints(dto);
        assertEquals(Integer.valueOf(beforeAvailable - 100), after.getAvailablePoints());
    }

    @Test(expected = RuntimeException.class)
    public void testSpendPointsInsufficient() {
        PointsOperationDTO dto = new PointsOperationDTO();
        dto.setMemberId(1L);
        dto.setPoints(999999);
        dto.setDescription("超额消费测试");
        memberService.spendPoints(dto);
    }

    @Test
    public void testGetPointsHistory() {
        List<PointsRecord> records = memberService.getPointsHistory(1L);
        assertNotNull(records);
        assertFalse(records.isEmpty());
    }

    @Test
    public void testEarnPointsLevelUp() {
        Member newMember = new Member();
        newMember.setUsername("levelup_test");
        newMember.setNickname("升级测试");
        Member registered = memberService.register(newMember);
        assertEquals(Long.valueOf(1L), registered.getLevelId());

        PointsOperationDTO dto = new PointsOperationDTO();
        dto.setMemberId(registered.getId());
        dto.setPoints(1200);
        dto.setDescription("大量积分触发升级");

        Member upgraded = memberService.earnPoints(dto);
        assertEquals(Long.valueOf(2L), upgraded.getLevelId());
    }
}
