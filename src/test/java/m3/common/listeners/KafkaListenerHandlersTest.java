package m3.common.listeners;

import m3.common.dto.rq.SendMeTimeRqDto;
import m3.common.dto.rq.SendUserAgentRqDto;
import m3.common.dto.rs.UpdateTimeRsDto;
import m3.common.mappers.CommonMapper;
import m3.common.services.impl.CommonServiceImpl;
import m3.lib.dto.rq.LogRqDto;
import m3.lib.dto.rq.StatisticRqDto;
import m3.lib.enums.ClientLogLevels;
import m3.lib.enums.StatisticEnum;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class KafkaListenerHandlersTest {

    private final CommonServiceImpl service = Mockito.mock(CommonServiceImpl.class);
    private final CommonMapper mapper = Mockito.mock(CommonMapper.class);

    private final KafkaListenerHandlers listener = new KafkaListenerHandlers(mapper, service);

    @Test
    void log() {
        // given
        boolean sendToTelegram = true;
        final var rq = LogRqDto.builder()
                .level(ClientLogLevels.DEBUG)
                .message("message")
                .userId(123L)
                .sendToTelegram(sendToTelegram)
                .build();

        // when
        listener.log(rq);

        // then
        verify(service, times(1))
                .log(eq(rq.getLevel()), eq(rq.getMessage()), eq(sendToTelegram));
    }

    @Test
    void saveUserAgent() {
        // given
        final var rq = SendUserAgentRqDto.builder()
                .userId(123L)
                .userAgentString("user-agent-string")
                .build();

        // when
        listener.saveUserAgent(rq);

        // then
        verify(service, times(1))
                .saveUserAgent(eq(rq.getUserId()), eq(rq.getUserAgentString()));
    }

    @Test
    void sendMeTime() {
        // given

        final var connectionId = 123L;
        final long mockedtimestamp = 1234L;
        final var rq = SendMeTimeRqDto.builder()
                .connectionId(connectionId)
                .build();
        //var expectedRsDto = UpdateTimeRsDto.builder().connectionId(connectionId).timestamp()build();
        when(service.getCurrentTime()).thenReturn(mockedtimestamp);
        when(mapper.toUpdateTimeRsDto(any(), any())).thenReturn(new UpdateTimeRsDto());

        // when
        var actualRs = listener.sendMeTime(rq);

        // then
        verify(service).getCurrentTime();
        verify(mapper).toUpdateTimeRsDto(eq(mockedtimestamp), eq(connectionId));
        assertThat(actualRs).isInstanceOf(UpdateTimeRsDto.class);
    }

    @Test
    void statistic() {
        // given
        final var rq = StatisticRqDto.builder()
                .userId(123L)
                .statId(StatisticEnum.ID_BUY_HEALTH)
                .build();

        // when
        listener.statistic(rq);

        // then
        verify(service, times(1))
                .statistic(
                        eq(rq.getUserId()),
                        eq(rq.getStatId()), rq.getParamA(), rq.getParamB());
    }
}
