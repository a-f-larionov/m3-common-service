package m3.users.dto.rq;


import lombok.*;
import lombok.experimental.SuperBuilder;
import m3.users.enums.StatisticEnum;

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
