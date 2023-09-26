package m3.users.services.impl;

import m3.users.dto.rq.LogRqDto;
import m3.users.enums.LogLevels;
import m3.users.mappers.CommonMapper;
import m3.users.services.CommonService;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

public class UserServiceImplTest {

    private final CommonMapper mapper = Mockito.mock(CommonMapper.class);
    private final CommonService service = new CommonServiceImpl();

    @Test
    void getCurrentTime() {
        // given - when
        Long timeStamp = service.getCurrentTime();

        // then
        assertThat(timeStamp)
                .isNotNull()
                .isCloseTo(System.currentTimeMillis() / 1000L, within(10L));

    }
}
