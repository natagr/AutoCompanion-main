package com.auto.companion.carinfo.controller;

import com.auto.companion.carinfo.model.CarInfoDto;
import com.auto.companion.carinfo.model.CarInfoForm;
import com.auto.companion.carinfo.service.CarInfoService;
import com.auto.companion.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarInfoController {

    private final CarInfoService carInfoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<CarInfoDto> getAllCarInfo(@PageableDefault(value = 5, direction = Sort.Direction.DESC, sort = "createdAt") Pageable pageable) {
        return carInfoService.getAllCarInfo(pageable);
    }

    @GetMapping("/get-garage")
    public Page<CarInfoDto> getAllCarsByUserId(Authentication authentication, Pageable pageable) {
        User user = (User) authentication.getPrincipal();
        return carInfoService.getAllCarsByUserId(user.getId(), pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CarInfoDto getCarInfoById(@PathVariable Long id) {
        return carInfoService.getCarInfoById(id);
    }

    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createCarInfo(@ModelAttribute CarInfoForm carInfoForm, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        carInfoService.createCarInfo(carInfoForm,user.getId());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateCarInfo(@PathVariable Long id, @RequestBody CarInfoForm carInfoForm) {
        carInfoService.updateCarInfo(id, carInfoForm);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCarInfo(@PathVariable Long id) {
        carInfoService.deleteCarInfo(id);
    }

}
