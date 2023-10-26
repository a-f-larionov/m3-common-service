package m3.common.dto.rq;

import lombok.*;
import lombok.experimental.SuperBuilder;
import m3.common.enums.ClientLogLevels;
import m3.lib.dto.rq.UserIdRqDto;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LogRqDto extends UserIdRqDto {

    public ClientLogLevels level;
    public String message;
    public String details;
    public Boolean sendToTelegram;
}
