package m3.common.services.impl;

import m3.common.mappers.UserAgentMapper;
import m3.common.services.CommonService;
import m3.lib.repositories.UserAgentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserAgentRepository userAgentRepository;
    @Mock
    private UserAgentMapper userAgentMapper;
    @InjectMocks
    private CommonServiceImpl service;

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
        verify(userAgentMapper, Mockito.times(1)).toEntity(userId, userAgent);
        verify(userAgentRepository, Mockito.times(1)).save(any());
    }
}
