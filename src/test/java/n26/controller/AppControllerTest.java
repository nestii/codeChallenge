package n26.controller;

import n26.StatisticsApplication;
import n26.service.StatisticsService;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StatisticsApplication.class)
@WebAppConfiguration
public class AppControllerTest {

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private AppController appController;

    @Autowired
    private WebApplicationContext webApplicationContext;


    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldReturn204IfTransactionOlderThanMinute() throws Exception {
        // given
        final String json = new JSONObject()
                .put("amount", "1.0")
                .put("timestamp", "1495828300830")
                .toString();
        // when
        mockMvc.perform(post("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                // then
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturn200IfTransactionNotOlderThanMinute() throws Exception {
        // given
        final String json = new JSONObject()
                .put("amount", "1.0")
                .put("timestamp", System.currentTimeMillis())
                .toString();
        // when
        mockMvc.perform(post("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                // then
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void shouldReturnGetStatistics() throws Exception {
        // when
        mockMvc.perform(get("/statistics"))
                // then
                .andExpect(status().isOk());
    }
}
