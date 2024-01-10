package com.auto.companion.servicehistory.controller;

import com.auto.companion.domain.model.ServiceHistory;
import com.auto.companion.domain.model.User;
import com.auto.companion.servicehistory.model.ServiceHistoryDto;
import com.auto.companion.servicehistory.service.ServiceHistoryService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/history")
public class ServiceHistoryController {

  private final ServiceHistoryService serviceHistoryService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<ServiceHistoryDto> getAllServiceHistory(Authentication authentication,
                                                      @RequestParam("vinCode") String vinCode) {
    User user = (User) authentication.getPrincipal();
    return serviceHistoryService.getAllServiceHistory(user.getId(),vinCode);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ServiceHistory getServiceHistoryById(@PathVariable Long id) {
    return serviceHistoryService.getServiceHistoryById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void addServiceHistory(@RequestBody ServiceHistoryDto serviceHistoryDto,
                                             Authentication authentication) {
    User user = (User) authentication.getPrincipal();
    serviceHistoryService.addServiceHistory(serviceHistoryDto, user.getId());
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public void updateServiceHistory(@PathVariable Long id, @RequestBody ServiceHistoryDto serviceHistoryDto) {
    serviceHistoryService.updateServiceHistory(id, serviceHistoryDto);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteServiceHistory(@PathVariable Long id) {
    serviceHistoryService.deleteServiceHistory(id);
  }
}
