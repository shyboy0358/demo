package com.example.demo.service;

import com.example.demo.dto.BenefitClaimDTO;
import com.example.demo.entity.BenefitRecord;
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
public class BenefitRecordServiceTest {

    @Autowired
    private BenefitRecordService benefitRecordService;

    @Test
    public void testGetRecordsByMemberId() {
        List<BenefitRecord> records = benefitRecordService.getRecordsByMemberId(1L);
        assertNotNull(records);
        assertThat(records.size(), greaterThanOrEqualTo(2));
    }

    @Test
    public void testGetRecordsByFamilyId() {
        List<BenefitRecord> records = benefitRecordService.getRecordsByFamilyId(1L);
        assertNotNull(records);
        assertThat(records.size(), greaterThanOrEqualTo(2));
    }

    @Test
    public void testCreateRecord() {
        BenefitRecord record = new BenefitRecord();
        record.setBenefitId(1L);
        record.setBenefitName("测试权益");
        record.setMemberId(1L);
        record.setFamilyId(1L);
        record.setStudentCode("STU001");
        record.setBenefitCategory("COURSE_COUPON");

        BenefitRecord created = benefitRecordService.createRecord(record);
        assertNotNull(created);
        assertNotNull(created.getId());
        assertEquals("PENDING", created.getStatus());
    }

    @Test
    public void testClaimBenefit() {
        BenefitClaimDTO dto = new BenefitClaimDTO();
        dto.setBenefitId(4L);
        dto.setMemberId(1L);
        dto.setFamilyId(1L);
        dto.setStudentCode("STU001");

        BenefitRecord claimed = benefitRecordService.claimBenefit(dto);
        assertNotNull(claimed);
        assertEquals("CLAIMED", claimed.getStatus());
        assertNotNull(claimed.getClaimTime());
    }
}
