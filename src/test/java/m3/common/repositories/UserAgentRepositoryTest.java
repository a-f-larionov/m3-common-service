package m3.common.repositories;

import m3.common.BaseDataJpaTest;
import m3.lib.entities.UserAgentEntity;
import m3.lib.repositories.UserAgentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Commit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest(properties = {
        "logging.level.org.springframework.jdbc.core=DEBUG",
        "logging.level.org.hibernate.SQL=DEBUG"
})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Commit
public class UserAgentRepositoryTest extends BaseDataJpaTest {

    @Autowired
    private UserAgentRepository userAgentRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void testSaveOne() {
        // given
        final var uid = 123L;
        final var agent = "user-agent-string";

        jdbcTemplate.update("DELETE FROM `user_agents`");
        final var entity = UserAgentEntity.builder()
                .uid(uid)
                .agent(agent)
                .build();
        // when
        userAgentRepository.save(entity);

        // then
        final var data = jdbcTemplate.queryForMap("SELECT * FROM `user_agents`");
        assertEquals(uid, Long.valueOf((Integer) data.get("uid")));
        assertEquals(agent, data.get("agent"));
    }
}
