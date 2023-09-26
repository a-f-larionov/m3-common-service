package m3.users.dto.rq;


import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SendUserAgentRqDto extends UserIdRqDto {

    private String userAgentString;
}
