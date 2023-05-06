package leanmanager2;

import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;
import org.hamcrest.Matchers;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = { LeanManager2.class })
@TestPropertySource(locations = { "classpath:application-test.properties" })
public class JUnit {

    // Fields
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    // Test preparation
    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    // Tests
    @Test
    public void clientIsRedirected() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/assets"));
    }

    @Test
    public void assetIndexAppears() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/assets"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("LeanManager 2")))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("Assets")));
    }
}