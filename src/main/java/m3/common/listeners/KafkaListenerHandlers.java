package m3.common.listeners;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import m3.common.dto.rq.LogRqDto;
import m3.common.dto.rq.SendMeTimeRqDto;
import m3.common.dto.rq.SendUserAgentRqDto;
import m3.common.dto.rq.StatisticRqDto;
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
@KafkaListener(topics = "topic-common", errorHandler = "validationErrorHandler")
public class KafkaListenerHandlers {

    private final CommonMapper commonMapper;
    private final CommonService commonService;

    @KafkaHandler
    public void log(LogRqDto rq) {
        commonService.log(rq.getLevel(), rq.getMessage(), rq.getDetails(), rq.getSendToTelegram());
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
}