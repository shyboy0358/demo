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
public class FamilyGroupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllFamilyGroups() throws Exception {
        mockMvc.perform(get("/api/families"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    public void testGetFamilyGroupDetail() throws Exception {
        mockMvc.perform(get("/api/families/1/detail"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.familyGroup.familyName").exists())
                .andExpect(jsonPath("$.data.members").isArray());
    }

    @Test
    public void testCreateFamilyGroup() throws Exception {
        String json = "{\"familyName\":\"控制器测试家庭\",\"ownerUserId\":1,"
                + "\"ownerName\":\"测试\",\"ownerPhone\":\"13900000001\"}";

        mockMvc.perform(post("/api/families")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.familyName").value("控制器测试家庭"));
    }

    @Test
    public void testRemoveFamilyMember() throws Exception {
        mockMvc.perform(get("/api/families/1/detail"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.members").isArray());

        mockMvc.perform(delete("/api/families/1/members/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));
    }
}
