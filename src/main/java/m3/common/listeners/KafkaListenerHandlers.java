package m3.common.listeners;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import m3.common.dto.rq.*;
import m3.common.dto.rs.UpdateTimeRsDto;
import m3.common.mappers.CommonMapper;
import m3.common.services.CommonService;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Slf4j
@KafkaListener(topics = "topic-common", groupId = "2",
        errorHandler = "validationErrorHandler")
public class KafkaListenerHandlers {

    private final CommonMapper commonMapper;
    private final CommonService commonService;

    @KafkaHandler
    public void log(@Valid LogRqDto rq) {
        commonService.log(rq.getLevel(), rq.getMessage(), rq.getDetails());
    }

    @KafkaHandler
    public void saveUserAgent(SendUserAgentRqDto rq) {
        commonService.saveUserAgent(rq.getUserId(), rq.getUserAgentString());
    }

    @KafkaHandler
    @SendTo("topic-client")
    public UpdateTimeRsDto sendMeTime(SendMeTimeRqDto rq) {
        return commonMapper.toUpdateTimeRsDto(
                commonService.getCurrentTime(),
                rq.getConnectionId());
    }

    @KafkaHandler
    public void statistic(StatisticRqDto rq) {
        commonService.statistic(rq.getUserId(), rq.getStatId());
    }

    @KafkaHandler
    public void sendToTelegram(TelegramRqDto rq) {
        commonService.sendToTelegram(rq.getMessage(), rq.getDetail());
    }
}