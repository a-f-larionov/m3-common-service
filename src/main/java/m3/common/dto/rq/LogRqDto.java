package m3.common.dto.rq;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import m3.common.enums.LogLevels;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LogRqDto extends UserIdRqDto {

    @NotNull
    public LogLevels level;
    public String message;
    public String details;
}
