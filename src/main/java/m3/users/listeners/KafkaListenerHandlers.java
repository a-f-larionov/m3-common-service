package m3.users.listeners;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import m3.users.dto.rq.LogRqDto;
import m3.users.dto.rq.SendMeTimeRqDto;
import m3.users.dto.rq.SendUserAgentRqDto;
import m3.users.dto.rq.StatisticRqDto;
import m3.users.dto.rs.UpdateTimeRsDto;
import m3.users.mappers.CommonMapper;
import m3.users.services.CommonService;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
@Slf4j
@KafkaListener(topics = "topic-common", groupId = "2",
        errorHandler = "validationErrorHandler")
//        containerFactory = "kafkaJsonListenerContainerFactory")
public class KafkaListenerHandlers {

    private final CommonMapper commonMapper;
    private final CommonService commonService;

    @KafkaHandler
    public void log(@Valid LogRqDto rq) {
        commonService.log(rq.getMessage(), rq.getLevel(), rq.getDetails(), rq.getType());
    }

    @KafkaHandler
    public void saveUserAgent(SendUserAgentRqDto rq) {
        commonService.saveUserAgent(rq.getUserAgentString());
    }

    @KafkaHandler
    @SendTo("topic-client")
    public UpdateTimeRsDto sendMeTime(SendMeTimeRqDto rq) {
        return commonMapper.toUpdateTimeRsDto(
                commonService.getCurrentTime());
    }

    @KafkaHandler
    public void statistic(StatisticRqDto rq) {
        commonService.statistic(rq.getUserId(), rq.getStatId());
    }
}