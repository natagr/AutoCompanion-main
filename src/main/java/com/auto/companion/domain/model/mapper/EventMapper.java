package com.auto.companion.domain.model.mapper;

import com.auto.companion.domain.model.Event;
import com.auto.companion.event.model.EventDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EventMapper {

    Event toEntity(EventDto eventDto);
    @Mapping(source = "user.id", target = "userId")
    EventDto toDto(Event event);

    void updateCarInfo(EventDto dto, @MappingTarget Event event);
}
