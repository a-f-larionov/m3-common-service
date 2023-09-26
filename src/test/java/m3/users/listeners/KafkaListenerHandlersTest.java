package m3.users.listeners;

import m3.users.dto.rq.SendMeTimeRqDto;
import m3.users.dto.rs.UpdateTimeRsDto;
import m3.users.mappers.CommonMapper;
import m3.users.services.impl.CommonServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class KafkaListenerHandlersTest {

    private final CommonServiceImpl service = Mockito.mock(CommonServiceImpl.class);
    private final CommonMapper mapper = Mockito.mock(CommonMapper.class);

    private final KafkaListenerHandlers listener = new KafkaListenerHandlers(mapper, service);

    @Test
    void sendMeTime() {
        // given
        var rq = SendMeTimeRqDto.builder().userId(123L).build();
        long mockedtimestamp = 1234L;
        when(service.getCurrentTime()).thenReturn(mockedtimestamp);
        when(mapper.toUpdateTimeRsDto(any())).thenReturn(new UpdateTimeRsDto());

        // when
        var actualRs = listener.sendMeTime(rq);

        // then
        verify(service).getCurrentTime();
        verify(mapper).toUpdateTimeRsDto(eq(mockedtimestamp));
        assertThat(actualRs).isInstanceOf(UpdateTimeRsDto.class);
    }
}
