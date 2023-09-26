package m3.users.services.functional;

import m3.users.BaseSpringBootTest;
import m3.users.services.impl.CommonServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest(properties = {
        "logging.level.org.hibernate.SQL=TRACE"
})
public class UserServiceFuncTest extends BaseSpringBootTest {

    @Autowired
    CommonServiceImpl commonService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void logOne() {

    }
}
