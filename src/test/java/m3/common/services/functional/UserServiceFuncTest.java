package m3.common.services.functional;

import m3.common.BaseSpringBootTest;
import m3.common.enums.ClientLogLevels;
import m3.common.enums.StatisticEnum;
import m3.common.services.impl.CommonServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(properties = {
        "logging.level.org.hibernate.SQL=TRACE"
})
public class UserServiceFuncTest extends BaseSpringBootTest {

    @Autowired
    CommonServiceImpl service;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void log() {
        final Boolean sendToTelegram = false;
        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        // given
        final var message = "msg";
        final var details = "details";
        final var level = ClientLogLevels.INFO;

        // when
        service.log(level, message, details, sendToTelegram);

        // then
        final String standardOutput = myOut.toString();
        assertThat(standardOutput)
                .contains("--- [    Test worker] m.c.services.impl.CommonServiceImpl      : msgdetails")
                .contains(" INFO ");
    }

    @Test
    void statistic() {
        // given
        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));
        final  var userId = 1234L;
        final  var statId = StatisticEnum.ID_BUY_HUMMER;
        // when
        service.statistic(userId, statId);

        // then
        saveUserAgent();
        final String standardOutput = myOut.toString();
        assertThat(standardOutput)
                .contains("--- [    Test worker] m.c.services.impl.CommonServiceImpl      : Stat400 Купил молоток")
                .contains(" INFO ");
    }

    @Test
    void getCurrentTime() {
        // given - when
        final Long timeStamp = service.getCurrentTime();

        // then
        assertThat(timeStamp)
                .isNotNull()
                .isCloseTo(System.currentTimeMillis(), within(1000L));
    }

    @Test
    void saveUserAgent() {
        // given
        final var userId = 123L;
        final var userAgentString = "user-agent-string";
        jdbcTemplate.update("DELETE FROM `user_agents`");

        // when
        service.saveUserAgent(userId, userAgentString);

        // then
        final var data = jdbcTemplate.queryForMap("SELECT * FROM `user_agents`");
        assertEquals(userId, Long.valueOf((Integer) data.get("uid")));
        assertEquals(userAgentString, data.get("agent"));
    }
}
