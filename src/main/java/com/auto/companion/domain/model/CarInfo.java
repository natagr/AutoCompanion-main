package com.auto.companion.domain.model;

import com.auto.companion.domain.model.base.AbstractIdentifiable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "car_infos")
public class CarInfo extends AbstractIdentifiable {

    @Column(name = "car_model")
    private String carModel;

    @Column(name = "vin_code")
    private String vinCode;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "all_info_about_car",columnDefinition = "TEXT")
    private String allInfoAboutCar;

    @Column(name = "imagedata", length = 1000)
    private byte[] imageData;
}
