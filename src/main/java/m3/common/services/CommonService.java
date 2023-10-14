package m3.common.services;

import m3.common.enums.LogLevels;
import m3.common.enums.StatisticEnum;

public interface CommonService {
    void log(LogLevels level, String message, String detail);

    void saveUserAgent(Long userId, String userAgentString);

    Long getCurrentTime();

    void statistic(Long userId, StatisticEnum stat);

    void sendToTelegram(String message, String detail);
}