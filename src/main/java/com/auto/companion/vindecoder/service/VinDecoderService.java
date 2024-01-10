package com.auto.companion.vindecoder.service;

import com.auto.companion.domain.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class VinDecoderService {

  @Value("${app.vin.apiUrl}")
  private String apiUrl;

  @Value("${app.vin.apiKey}")
  private String apiKey;
  private final RestTemplate restTemplate;

  //todo: add display a more info error massage  response.getStatusCode()
  public Object decodeVin(String vin) {
    String url = apiUrl + vin + "?apikey=" + apiKey;
    ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);

    if (response.getStatusCode().is2xxSuccessful()) {
      return response.getBody();
    } else {
      throw new GlobalException(HttpStatus.INTERNAL_SERVER_ERROR,
          "Vin decoder service does not work.");
    }
  }
}
