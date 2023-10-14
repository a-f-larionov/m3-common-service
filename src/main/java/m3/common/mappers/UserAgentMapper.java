package m3.common.mappers;

import m3.common.entities.UserAgentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserAgentMapper {

    UserAgentEntity toEntity(Long uid, String agent);
}
