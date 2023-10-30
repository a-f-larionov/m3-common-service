package m3.common.mappers;

import m3.lib.entities.UserAgentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserAgentMapper {

    @Mapping(target = "id", ignore = true)
    UserAgentEntity toEntity(Long uid, String agent);
}
