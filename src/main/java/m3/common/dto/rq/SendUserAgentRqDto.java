package m3.common.dto.rq;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;
import m3.lib.dto.rq.UserIdRqDto;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SendUserAgentRqDto extends UserIdRqDto {
    @NotBlank
    private String userAgentString;
}
