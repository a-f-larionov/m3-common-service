package m3.common.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import m3.common.enums.ClientLogLevels;
import m3.lib.enums.StatisticEnum;
import m3.common.mappers.UserAgentMapper;
import m3.common.services.CommonService;
import m3.lib.helpers.TelegramSender;
import m3.lib.repositories.UserAgentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static m3.common.enums.ClientLogLevels.INFO;

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
    public void log(ClientLogLevels level, String message, String details, @NonNull Boolean sendToTelegram) {

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
        log(
                INFO,
                "Stat",
                stat.getId() + " " + stat.getTitle(),
                true
        );
    }

}
