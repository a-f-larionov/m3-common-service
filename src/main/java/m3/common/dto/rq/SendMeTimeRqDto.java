package m3.common.dto.rq;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class SendMeTimeRqDto {
    @NotNull
    private Long connectionId;
}
