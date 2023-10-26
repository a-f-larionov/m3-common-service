package m3.common.dto.rq;


import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class SendMeTimeRqDto {
    private Long connectionId;
}
