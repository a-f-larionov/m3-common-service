package m3.common.listeners;

import m3.common.dto.rq.SendMeTimeRqDto;
import m3.common.dto.rs.UpdateTimeRsDto;
import m3.common.mappers.CommonMapper;
import m3.common.services.impl.CommonServiceImpl;
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
        var connectionId = 123L;
        long mockedtimestamp = 1234L;
        var rq = SendMeTimeRqDto.builder()
                .connectionId(connectionId)
                .build();
        //var expectedRsDto = UpdateTimeRsDto.builder().connectionId(connectionId).timestamp()build();
        when(service.getCurrentTime()).thenReturn(mockedtimestamp);
        when(mapper.toUpdateTimeRsDto(any(),any())).thenReturn(new UpdateTimeRsDto());

        // when
        var actualRs = listener.sendMeTime(rq);

        // then
        verify(service).getCurrentTime();
        verify(mapper).toUpdateTimeRsDto(eq(mockedtimestamp), eq(mockedtimestamp));
        assertThat(actualRs).isInstanceOf(UpdateTimeRsDto.class);
    }
}
