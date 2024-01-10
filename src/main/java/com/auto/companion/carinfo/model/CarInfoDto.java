package com.auto.companion.carinfo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarInfoDto {
    private Long id;
    private String carModel;
    private String vinCode;
    private String allInfoAboutCar;
    private byte[] imageData;
}
