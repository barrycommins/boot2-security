package com.barrycommins.boot2security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class Boot2SecurityApplicationTests {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }
    @Test
    @WithMockUser(username = "john", roles = { "VIEWER" })
    public void getWithWrongRole() throws Exception {
        mockMvc.perform(get("/hello")).andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(username = "barry", roles = { "ADMIN" })
    public void getWithCorrectRole() throws Exception {
        mockMvc.perform(get("/hello")).andExpect(status().is2xxSuccessful());
    }

}
