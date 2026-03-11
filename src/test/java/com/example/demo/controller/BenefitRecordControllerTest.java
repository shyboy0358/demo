package com.example.demo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BenefitRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetRecordsByMember() throws Exception {
        mockMvc.perform(get("/api/benefit-records/member/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    public void testGetRecordsByFamily() throws Exception {
        mockMvc.perform(get("/api/benefit-records/family/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    public void testCreateRecord() throws Exception {
        String json = "{\"benefitId\":1,\"benefitName\":\"测试权益\","
                + "\"memberId\":1,\"familyId\":1,\"studentCode\":\"STU001\","
                + "\"benefitCategory\":\"COURSE_COUPON\"}";

        mockMvc.perform(post("/api/benefit-records")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.status").value("PENDING"));
    }

    @Test
    public void testClaimBenefit() throws Exception {
        String json = "{\"benefitId\":88,\"memberId\":1,\"familyId\":1,\"studentCode\":\"STU001\"}";

        mockMvc.perform(post("/api/benefit-records/claim")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.status").value("CLAIMED"));
    }
}
