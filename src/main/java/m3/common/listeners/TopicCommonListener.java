package m3.common.listeners;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import m3.common.dto.rq.SendMeTimeRqDto;
import m3.common.dto.rq.SendUserAgentRqDto;
import m3.common.dto.rs.UpdateTimeRsDto;
import m3.common.mappers.CommonMapper;
import m3.common.services.CommonService;
import m3.lib.dto.rq.LogRqDto;
import m3.lib.dto.rq.StatisticRqDto;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Slf4j
@KafkaListener(topics = "topic-common")
public class TopicCommonListener {

    private final CommonMapper commonMapper;
    private final CommonService commonService;

    @KafkaHandler
    public void log(@Valid LogRqDto rq) {
        commonService.log(rq.getLevel(), rq.getMessage(), rq.getSendToTelegram());
    }

    @KafkaHandler
    public void statistic(@Valid StatisticRqDto rq) {
        commonService.statistic(rq.getUserId(), rq.getStatId(), rq.getParamA(), rq.getParamB());
    }

    @KafkaHandler
    public void saveUserAgent(@Valid SendUserAgentRqDto rq) {
        commonService.saveUserAgent(rq.getUserId(), rq.getUserAgentString());
    }

    @KafkaHandler
    @SendTo("topic-client")
    public UpdateTimeRsDto sendMeTime(@Valid SendMeTimeRqDto rq) {
        return commonMapper.toUpdateTimeRsDto(
                commonService.getCurrentTime(),
                rq.getConnectionId());
    }
}