package m3.users.services;

import m3.users.enums.LogLevels;
import m3.users.enums.LogType;
import m3.users.enums.StatisticEnum;

public interface CommonService {
     void log(String message, LogLevels level, String details, LogType type) ;

    Long getCurrentTime();

    void saveUserAgent(String userAgentString);

    void statistic(Long userId, StatisticEnum stat);
}