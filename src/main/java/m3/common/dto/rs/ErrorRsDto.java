package m3.common.dto.rs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import m3.common.commons.ErrorCodes;

@AllArgsConstructor
@Getter
public class ErrorRsDto {

    private final ErrorCodes errorCodes;
    private final String message;
    private final Long userId;
    private final String packetFrom;
}
