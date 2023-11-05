package m3.common.dto.rq;


import lombok.*;
import lombok.experimental.SuperBuilder;
import m3.lib.enums.StatisticEnum;
import m3.lib.dto.rq.UserIdRqDto;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StatisticRqDto extends UserIdRqDto {
    private StatisticEnum statId;
}
