package m3.common.dto.rq;


import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TelegramRqDto extends UserIdRqDto{

    private String message;
    private String detail;
}
