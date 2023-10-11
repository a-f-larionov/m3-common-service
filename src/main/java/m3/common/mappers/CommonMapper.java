package m3.common.mappers;

import m3.common.dto.rs.UpdateTimeRsDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommonMapper {

    UpdateTimeRsDto toUpdateTimeRsDto(Long timestamp, Long connectionId);
}
