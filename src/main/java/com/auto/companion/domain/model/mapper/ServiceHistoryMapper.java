package com.auto.companion.domain.model.mapper;

import com.auto.companion.domain.model.ServiceHistory;
import com.auto.companion.servicehistory.model.ServiceHistoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ServiceHistoryMapper {

  ServiceHistory toEntity(ServiceHistoryDto serviceHistoryDto);

  ServiceHistoryDto toDto(ServiceHistory serviceHistory);

  void updateServiceHistoryInfo(
      ServiceHistoryDto dto, @MappingTarget ServiceHistory serviceHistory);
}
