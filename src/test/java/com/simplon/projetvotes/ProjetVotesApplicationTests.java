package com.simplon.projetvotes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
@SpringBootTest
@AutoConfigureMockMvc
class ProjetVotesApplicationTests {

    @Autowired
    public MockMvc mockMvc;

    @Test
    public void testGetProjet() throws Exception {
        mockMvc.perform(get("/projets")).andExpect(status().isOk()).andExpect(jsonPath("$[0].creePar", is("Solenne")));
    }

}
**/