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

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllMembers() throws Exception {
        mockMvc.perform(get("/api/members"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    public void testGetMemberProfile() throws Exception {
        mockMvc.perform(get("/api/members/1/profile"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.member.username").value("zhangsan"))
                .andExpect(jsonPath("$.data.currentLevel").exists())
                .andExpect(jsonPath("$.data.benefits").isArray());
    }

    @Test
    public void testRegisterMember() throws Exception {
        String json = "{\"username\":\"controller_test\",\"nickname\":\"控制器测试\","
                + "\"phone\":\"13900139000\",\"email\":\"test@example.com\"}";

        mockMvc.perform(post("/api/members/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.username").value("controller_test"))
                .andExpect(jsonPath("$.data.totalPoints").value(0));
    }

    @Test
    public void testEarnPoints() throws Exception {
        String json = "{\"memberId\":2,\"points\":500,\"description\":\"API测试积分获取\"}";

        mockMvc.perform(post("/api/members/points/earn")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.totalPoints").exists());
    }

    @Test
    public void testGetPointsHistory() throws Exception {
        mockMvc.perform(get("/api/members/1/points/history"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray());
    }
}
