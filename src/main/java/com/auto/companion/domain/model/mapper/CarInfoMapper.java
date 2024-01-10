package com.auto.companion.domain.model.mapper;

import com.auto.companion.carinfo.model.CarInfoDto;
import com.auto.companion.carinfo.model.CarInfoForm;
import com.auto.companion.domain.model.CarInfo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CarInfoMapper {

    CarInfo toEntity(CarInfoForm carInfoForm);

    CarInfoDto toDto(CarInfo carInfo);

    void updateCarInfo(CarInfoForm form, @MappingTarget CarInfo carInfo);

}
