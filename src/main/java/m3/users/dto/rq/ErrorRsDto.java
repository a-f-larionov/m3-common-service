package m3.users.dto.rq;

import lombok.AllArgsConstructor;
import m3.users.commons.ErrorCodes;

@AllArgsConstructor
public class ErrorRsDto {

    private final ErrorCodes errorCodes;
    private final String message;
}
