package m3.common.dto.rq;

import lombok.AllArgsConstructor;
import m3.common.commons.ErrorCodes;

@AllArgsConstructor
public class ErrorRsDto {

    private final ErrorCodes errorCodes;
    private final String message;
}
