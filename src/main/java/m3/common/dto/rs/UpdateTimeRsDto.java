package m3.common.dto.rs;


import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UpdateTimeRsDto {
    private Long connectionId;
    private Long timestamp;
}
