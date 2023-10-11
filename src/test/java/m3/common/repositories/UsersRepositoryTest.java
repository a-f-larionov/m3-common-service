package m3.common.repositories;

import m3.common.BaseDataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Commit;

@DataJpaTest(properties = {
        "logging.level.org.springframework.jdbc.core=DEBUG",
        "logging.level.org.hibernate.SQL=DEBUG"
})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Commit
public class UsersRepositoryTest extends BaseDataJpaTest {
//
//    @Autowired
//    private StatisticRepository statisticRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    @Test
//    void testUpdateLastLogout() {
//        // given
//        jdbcTemplate.update("INSERT INTO users (socNetTypeId, socNetUserId) VALUES (?, ?)", 1, 1);
//        jdbcTemplate.update("INSERT INTO users (socNetTypeId, socNetUserId) VALUES (?, ?)", 1, 2);
//        var userId = (Long) jdbcTemplate.queryForMap("SELECT MAX(id) as maxId FROM users").get("maxId");
//        var newLogoutTS = System.currentTimeMillis() / 1000;
//
//        // when
//        int result = usersRepository.updateLastLogout(userId, newLogoutTS);
//
//        // then
//        assertEquals(1, result);
//        Long logout_tm = (long) jdbcTemplate.queryForMap("SELECT logout_tm FROM users WHERE id=" + userId).get("logout_tm");
//        assertEquals(newLogoutTS, logout_tm);
//    }
}
