package m3.common.dto.rq;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;
import m3.lib.dto.rq.UserIdRqDto;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TelegramRqDto extends UserIdRqDto {
    @NotBlank
    private String message;
}
