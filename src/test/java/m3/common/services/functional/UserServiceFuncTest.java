package m3.common.services.functional;

import m3.common.BaseSpringBootTest;
import m3.common.services.CommonService;
import m3.lib.enums.ClientLogLevels;
import m3.lib.enums.StatisticEnum;
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
    CommonService service;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void log() {
        // given
        final Boolean sendToTelegram = false;
        final var message = " my test message ";
        final var level = ClientLogLevels.INFO;

        final ByteArrayOutputStream myOut = redirectStdOut();

        // when
        service.log(level, message, sendToTelegram);

        // then
        final String standardOutput = myOut.toString();
        assertThat(standardOutput)
                .contains(" INFO ")
                .contains("common-service")
                .contains(" logger                                   :  my test message ");
    }

    @Test
    void statistic() {
        // given
        final ByteArrayOutputStream myOut = redirectStdOut();
        final var userId = 1234L;
        final var statId = StatisticEnum.ID_BUY_HUMMER;
        // when
        service.statistic(userId, statId, "param-a", "param-b");

        // then
        saveUserAgent();
        final String standardOutput = myOut.toString();
        assertThat(standardOutput)
                .contains(" INFO ")
                .contains(" logger                                   : Stat uid:1234 400 Купил молоток param-a param-b");
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
        jdbcTemplate.update("delete from user_agents where uid between -1 AND 1000000000000000000");

        // when
        service.saveUserAgent(userId, userAgentString);

        // then
        final var data = jdbcTemplate.queryForMap("SELECT * FROM user_agents");
        assertEquals(userId, (Long) data.get("uid"));
        assertEquals(userAgentString, data.get("agent"));
    }

    private static ByteArrayOutputStream redirectStdOut() {
        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));
        return myOut;
    }
}
