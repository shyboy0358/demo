package com.example.demo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MemberLevelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllLevels() throws Exception {
        mockMvc.perform(get("/api/levels"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.length()").value(greaterThanOrEqualTo(5)));
    }

    @Test
    public void testGetLevelById() throws Exception {
        mockMvc.perform(get("/api/levels/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.levelName").value("普通会员"))
                .andExpect(jsonPath("$.data.levelCode").value("NORMAL"));
    }

    @Test
    public void testGetLevelDetail() throws Exception {
        mockMvc.perform(get("/api/levels/5/detail"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.level.levelName").value("钻石会员"))
                .andExpect(jsonPath("$.data.benefits").isArray())
                .andExpect(jsonPath("$.data.benefits.length()").value(6));
    }

    @Test
    public void testGetLevelByPoints() throws Exception {
        mockMvc.perform(get("/api/levels/match").param("points", "7000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.levelCode").value("GOLD"));
    }

    @Test
    public void testCreateLevel() throws Exception {
        String json = "{\"levelName\":\"超级VIP\",\"levelCode\":\"SVIP\",\"levelSort\":6,"
                + "\"minPoints\":100000,\"discount\":0.70,\"pointMultiplier\":10.0,"
                + "\"description\":\"超级VIP专属\"}";

        mockMvc.perform(post("/api/levels")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.levelName").value("超级VIP"))
                .andExpect(jsonPath("$.data.levelCode").value("SVIP"));
    }
}
