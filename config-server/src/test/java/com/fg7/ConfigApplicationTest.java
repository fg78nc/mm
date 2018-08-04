package com.fg7;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class ConfigApplicationTest {

    private static final String CUSTOMER_SERVICE_URL = "/customer-service.yml";
    private static final String SHIPPING_SERVICE_URL = "/shipping-service.yml";

    @Autowired
    MockMvc mockMvc;

    @Test
    public void customerServiceConfigurationQueryShouldReturnConfigurationData() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(CUSTOMER_SERVICE_URL))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.TEXT_PLAIN))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("config-server-ok")));

    }

    @Test
    public void shippingServiceConfigurationQueryShouldReturnConfigurationData() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(SHIPPING_SERVICE_URL))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.TEXT_PLAIN))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("config-server-ok")));
    }

}