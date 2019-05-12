package net.quadratic.dao;

import net.quadratic.config.DataBaseConfig;
import net.quadratic.entity.Params;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.util.MatcherAssertionErrors.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataBaseConfig.class, ParamsDaoTest.Config.class})
@WebAppConfiguration
public class ParamsDaoTest {

    @Configuration
    static class Config {

        @Bean
        public ParamsDAO paramsDAO() {
            return new ParamsDAO();
        }
    }

    @Autowired
    private ParamsDAO paramsDAO;


    @Test
    @Transactional
    @Rollback
    public void saveEmpty() {
        long result = paramsDAO.save(new Params());
        assertThat(result, Is.is(1L));
        result = paramsDAO.save(new Params());
        assertThat(result, Is.is(2L));
        Params params = new Params();
        params.setA(5);
        params.setB(6);
        params.setC(8);
        result = paramsDAO.save(params);
        assertThat(result, Is.is(3L));
    }

}
