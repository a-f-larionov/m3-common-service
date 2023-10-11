package m3.common.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import m3.common.enums.LogLevels;
import m3.common.enums.LogType;
import m3.common.enums.StatisticEnum;
import m3.common.services.CommonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class CommonServiceImpl implements CommonService {

    public void log(String message, LogLevels level, String details, LogType type) {

        //@todo test to null args passed

        //case Logs.TYPE_VK_PAYMENTS:
        //                FS.writeFile(CONST_DIR_SERVER + '/logs/vk_payments.log', logText + details + "\r\n", {flag: 'a'}, function () {
        //                });
        //                telega = true;
        //                break;
        //            case Logs.TYPE_VK_STUFF:
        //                FS.writeFile(CONST_DIR_SERVER + '/logs/vk_stuff.log', logText + details + "\r\n", {flag: 'a'}, function () {
        //                });
        //                break;
        //            case Logs.TYPE_VK_HEALTH:
        //                FS.writeFile(CONST_DIR_SERVER + '/logs/vk_health.log', logText + details + "\r\n", {flag: 'a'}, function () {
        //                });
        //                break;
        //            case Logs.TYPE_CLIENT_DEBUG_INFO:
        //                FS.writeFile(CONST_DIR_SERVER + '/logs/client.log', logText + details + "\r\n", {flag: 'a'}, function () {
        //                });
        //                telega = true;
        //                break;
        //            case Logs.CHANNEL_TELEGRAM:
        //                telega = true;
        //                break;
        //        }
        //        if (level >= Logs.LEVEL_ALERT) telega = true;
        //        if (level === Logs.LEVEL_ERROR || level === Logs.LEVEL_FATAL_ERROR) {
        //            if (CONST_IS_CLIENT_SIDE) {
        //                //@todo client errors channel
        //                SAPILogs.log(message, level, details);
        //            }
        //        }
        //        // если это фатальная ошибка - завершим работу программы.
        //
        //        if (CONST_IS_SERVER_SIDE && telega) {
        //            telegramSent(message + details);
        //        }
        //        if (level === Logs.LEVEL_FATAL_ERROR) {
        //            throw new Error("Vse polamalos'!");
        //        }
        switch (level) {
            case TRACE -> log.trace(message + details);
            case DEBUG -> log.debug(message + details);
            case INFO -> log.info(message + details);
            case WARN -> log.warn(message + details);
            case ERROR -> log.error(message + details);
        }
    }

    public Long getCurrentTime() {
        return (long) Math.floor((double) System.currentTimeMillis() / 1000D);
    }

    public void saveUserAgent(String userAgentString) {
        // "INSERT INTO user_agents (`uid`, `agent`) VALUES " +
        //            "( " + parseInt(userId) +
        //            ", " + DB.escape(string) + "" +
        //            " )", function () {
        //            }
    }

    public void statistic(Long userId, StatisticEnum stat) {
        log("message", LogLevels.INFO, "", LogType.STAT_INFO);
    }
}
