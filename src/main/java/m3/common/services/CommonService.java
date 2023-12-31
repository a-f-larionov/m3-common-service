package m3.common.services;

import m3.lib.enums.ClientLogLevels;
import m3.lib.enums.StatisticEnum;

public interface CommonService {
    void log(ClientLogLevels level, String message, Boolean sendToTelegram);

    void saveUserAgent(Long userId, String userAgentString);

    Long getCurrentTime();

    void statistic(Long userId, StatisticEnum stat, String paramA, String paramB);

}