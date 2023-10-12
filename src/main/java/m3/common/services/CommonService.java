package m3.common.services;

import m3.common.enums.LogLevels;
import m3.common.enums.LogType;
import m3.common.enums.StatisticEnum;

public interface CommonService {
    void log(String message, LogLevels level, String details, LogType type);

    void saveUserAgent(Long userId, String userAgentString);

    Long getCurrentTime();

    void statistic(Long userId, StatisticEnum stat);
}