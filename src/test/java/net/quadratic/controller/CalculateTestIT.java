package net.quadratic.controller;

import net.quadratic.config.MessageSourceConfiguration;
import net.quadratic.config.WebAppConfig;
import net.quadratic.dto.ParamsDto;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebAppConfig.class})
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CalculateTestIT {

    private final static String URL = "/calculate";

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MessageSourceConfiguration message;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void aIsZero() throws Exception {
        ParamsDto params = new ParamsDto();
        mockMvc.perform(
                post(URL)
                        .flashAttr("params", params)
        )
                .andExpect(status().isOk())
                .andExpect(model().attribute("message", Is.is(message.getMessage("exception.a.is_zero"))));

    }

    @Test
    public void solutionWithTwoResults() throws Exception {
        Double x1 = -5.646;
        Double x2 = -0.354;
        ParamsDto params = new ParamsDto();
        params.setA(1);
        params.setB(6);
        params.setC(2);

        mockMvc.perform(
                post(URL)
                        .flashAttr("params", params)
        )
                .andExpect(status().isOk())
                .andExpect(model().attribute("message", Is.is(message.getMessage("message.success.two_results", new Object[]{x1.toString(), x2.toString()}))));

    }

    @Test
    public void solutionWithOneResult() throws Exception {
        ParamsDto params = new ParamsDto();
        params.setA(-5);
        params.setB(0);
        params.setC(0);

        mockMvc.perform(
                post(URL)
                        .flashAttr("params", params)
        )
                .andExpect(status().isOk())
                .andExpect(model().attribute("message", Is.is(message.getMessage("message.success.one_results", new Object[]{0}))));

    }

    @Test
    public void solutionWithoutResult() throws Exception {
        ParamsDto params = new ParamsDto();
        params.setA(1);
        params.setB(2);
        params.setC(2);

        mockMvc.perform(
                post(URL)
                        .flashAttr("params", params)
        )
                .andExpect(status().isOk())
                .andExpect(model().attribute("message", Is.is(message.getMessage("message.success.no_results"))));

    }
}
