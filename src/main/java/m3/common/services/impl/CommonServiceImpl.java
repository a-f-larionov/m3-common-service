package m3.common.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import m3.common.enums.LogLevels;
import m3.common.enums.StatisticEnum;
import m3.lib.common.helpers.TelegramSender;
import m3.common.mappers.UserAgentMapper;
import m3.common.repositories.UserAgentRepository;
import m3.common.services.CommonService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class CommonServiceImpl implements CommonService {

    @Value("${alerter.telegram.token}")
    private String teleToken;
    @Value("${alerter.telegram.chatId}")
    private String chatId;

    private final UserAgentRepository userAgentRepository;
    private final UserAgentMapper userAgentMapper;

    @Override
    public void log(LogLevels level, String message, String details, Boolean sendToTelegram) {

        switch (level) {
            case TRACE -> log.trace(message + details);
            case DEBUG -> log.debug(message + details);
            case INFO -> log.info(message + details);
            case WARN -> log.warn(message + details);
            case ERROR -> log.error(message + details);
        }

        if (Boolean.TRUE.equals(sendToTelegram)) {
            TelegramSender.getInstance().sendToTelegram(
                    message + " " + details,
                    teleToken, chatId
            );
        }
    }

    @Override
    public Long getCurrentTime() {
        return (long) Math.floor((double) System.currentTimeMillis());
    }

    @Override
    public void saveUserAgent(Long userId, String userAgent) {
        userAgentRepository.save(userAgentMapper.toEntity(userId, userAgent));
    }

    @Override
    public void statistic(Long userId, StatisticEnum stat) {
        log(LogLevels.INFO, "Stat", stat.getId() + " " + stat.getTitle(), false);
    }

}
