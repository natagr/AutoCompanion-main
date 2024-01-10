package com.auto.companion.vindecoder.controller;

import com.auto.companion.vindecoder.service.VinDecoderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vin")
@RequiredArgsConstructor
public class VinDecoderController {

  private final VinDecoderService vinDecoderService;

  @GetMapping("/{vin}")
  @ResponseStatus(HttpStatus.OK)
  public Object decodeVin(@PathVariable String vin) {
    return vinDecoderService.decodeVin(vin);
  }
}
