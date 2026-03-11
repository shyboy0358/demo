package com.example.demo.service;

import com.example.demo.dto.MemberLevelDetailDTO;
import com.example.demo.entity.MemberLevel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberLevelServiceTest {

    @Autowired
    private MemberLevelService levelService;

    @Test
    public void testGetAllLevels() {
        List<MemberLevel> levels = levelService.getAllLevels();
        assertNotNull(levels);
        assertEquals(5, levels.size());
        assertEquals("普通会员", levels.get(0).getLevelName());
        assertEquals("钻石会员", levels.get(4).getLevelName());
    }

    @Test
    public void testGetLevelById() {
        MemberLevel level = levelService.getLevelById(1L);
        assertNotNull(level);
        assertEquals("NORMAL", level.getLevelCode());
        assertEquals(Integer.valueOf(0), level.getMinPoints());
    }

    @Test
    public void testGetLevelDetail() {
        MemberLevelDetailDTO detail = levelService.getLevelDetail(3L);
        assertNotNull(detail);
        assertEquals("金卡会员", detail.getLevel().getLevelName());
        assertNotNull(detail.getBenefits());
        assertEquals(4, detail.getBenefits().size());
    }

    @Test
    public void testGetLevelByPoints() {
        MemberLevel level = levelService.getLevelByPoints(0);
        assertEquals("NORMAL", level.getLevelCode());

        level = levelService.getLevelByPoints(1500);
        assertEquals("SILVER", level.getLevelCode());

        level = levelService.getLevelByPoints(25000);
        assertEquals("PLATINUM", level.getLevelCode());

        level = levelService.getLevelByPoints(100000);
        assertEquals("DIAMOND", level.getLevelCode());
    }

    @Test(expected = RuntimeException.class)
    public void testGetLevelByIdNotFound() {
        levelService.getLevelById(999L);
    }
}
