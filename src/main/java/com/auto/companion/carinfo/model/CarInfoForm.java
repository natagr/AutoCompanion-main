package com.auto.companion.carinfo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class CarInfoForm {

    private String carModel;
    private String vinCode;
    private String allInfoAboutCar;
    private MultipartFile image;

}
