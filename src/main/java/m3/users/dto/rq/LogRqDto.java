package m3.users.dto.rq;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import m3.users.enums.LogLevels;
import m3.users.enums.LogType;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LogRqDto extends UserIdRqDto {

    public String message;
    @NotNull
    public LogLevels level;
    public String details;
    public LogType type;
}
