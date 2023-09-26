package m3.users.mappers;

import m3.users.dto.rs.UpdateTimeRsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommonMapper {

    @Mapping(ignore = true, target = "userId")
    UpdateTimeRsDto toUpdateTimeRsDto(Long timestamp);
}
