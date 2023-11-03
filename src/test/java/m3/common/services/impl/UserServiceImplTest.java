package m3.common.services.impl;

import m3.common.mappers.CommonMapper;
import m3.common.mappers.UserAgentMapper;
import m3.common.services.CommonService;
import m3.lib.repositories.UserAgentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class UserServiceImplTest {

    private final CommonMapper mapper = Mockito.mock(CommonMapper.class);
    private final UserAgentRepository userAgentRepository = Mockito.mock(UserAgentRepository.class);
    private final UserAgentMapper userAgentMapper = Mockito.mock(UserAgentMapper.class);
    private final CommonService service = new CommonServiceImpl(userAgentRepository, userAgentMapper);


    @Test
    void log(){
        // do not remove it
        // and see m3.common.services.functional.UserServiceFuncTest.log
    }

    @Test
    void statistic(){
        // do not remove it
        // see m3.common.services.functional.UserServiceFuncTest.statistic
    }

    @Test
    void getCurrentTime() {
        // given - when
        Long timeStamp = service.getCurrentTime();

        // then
        assertThat(timeStamp)
                .isNotNull()
                .isCloseTo(System.currentTimeMillis(), within(1000L));
    }
    @Test
    void saveUserAgent() {
        // given
        final var userId = 123L;
        final var userAgent = "user-agent-string";

        // when
        service.saveUserAgent(userId, userAgent);

        // then
        verify(userAgentRepository, Mockito.times(1)).save(any());
    }
}
