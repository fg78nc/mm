package com.fg7;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class EurekaServerApplicationTest {

    private static final String EUREKA_HEALTH_CHECK_URL = "http://localhost:8761/actuator/health";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void EurekaServerShouldStartUp() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(EUREKA_HEALTH_CHECK_URL))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("status").value("UP"));

    }


}