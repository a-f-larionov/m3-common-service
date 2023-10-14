package m3.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum LogType {
    VK_PAYMENTS(1),
    VK_STUFF(2),
    VK_HEALTH(3),
    CLIENT_INFO(4),
    STAT_INFO(5)
    ;

    private final int type;
}
