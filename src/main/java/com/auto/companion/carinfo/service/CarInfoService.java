package com.auto.companion.carinfo.service;

import com.auto.companion.carinfo.model.CarInfoDto;
import com.auto.companion.carinfo.model.CarInfoForm;
import com.auto.companion.domain.model.CarInfo;
import com.auto.companion.domain.model.User;
import com.auto.companion.domain.model.mapper.CarInfoMapper;
import com.auto.companion.domain.repository.CarInfoRepository;
import com.auto.companion.domain.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CarInfoService {

    private final CarInfoRepository carInfoRepository;
    private final CarInfoMapper carInfoMapper;
    private final UserRepository userRepository;

    @Transactional
    @SneakyThrows
    public void createCarInfo(CarInfoForm carInfoForm, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);

        CarInfo carInfo = new CarInfo();
        carInfo.setCarModel(carInfoForm.getCarModel());
        carInfo.setAllInfoAboutCar(carInfoForm.getAllInfoAboutCar());
        carInfo.setUser(user);
        carInfo.setVinCode(carInfoForm.getVinCode());
        carInfo.setImageData(carInfoForm.getImage().getBytes());
        carInfoRepository.save(carInfo);
    }

    @Transactional(readOnly = true)
    public Page<CarInfoDto> getAllCarInfo(Pageable pageable) {
        return carInfoRepository.findAll(pageable).map(carInfoMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<CarInfoDto> getAllCarsByUserId(Long userId, Pageable pageable) {
        return carInfoRepository.findAllByUserId(userId, pageable).map(carInfoMapper::toDto);
    }

    @Transactional(readOnly = true)
    public CarInfoDto getCarInfoById(Long id) {
        CarInfo carInfo = carInfoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return carInfoMapper.toDto(carInfo);
    }

    @Transactional
    public void updateCarInfo(Long id, CarInfoForm carInfoForm) {
        CarInfo carInfo = carInfoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        carInfoMapper.updateCarInfo(carInfoForm, carInfo);
        carInfoRepository.save(carInfo);
    }

    @Transactional
    public void deleteCarInfo(Long id) {
        CarInfo carInfo = carInfoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        carInfoRepository.delete(carInfo);
    }


}
