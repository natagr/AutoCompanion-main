package com.auto.companion.servicehistory.service;

import com.auto.companion.domain.model.Notification;
import com.auto.companion.domain.model.ServiceHistory;
import com.auto.companion.domain.model.User;
import com.auto.companion.domain.model.mapper.ServiceHistoryMapper;
import com.auto.companion.domain.repository.ServiceHistoryRepository;
import com.auto.companion.domain.repository.UserRepository;
import com.auto.companion.servicehistory.model.ServiceHistoryDto;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@RequiredArgsConstructor
public class ServiceHistoryService {

  private final ServiceHistoryRepository serviceHistoryRepository;
  private final ServiceHistoryMapper serviceHistoryMapper;
  private final UserRepository userRepository;

  @Transactional(readOnly = true)
  public List<ServiceHistoryDto> getAllServiceHistory(Long userId, String vinCode) {
    return serviceHistoryRepository.findByUserIdAndVinCode(userId,vinCode)
            .stream()
            .map(serviceHistoryMapper::toDto)
            .toList();
  }

  @Transactional(readOnly = true)
  public ServiceHistory getServiceHistoryById(Long id) {
    return serviceHistoryRepository.findById(id).orElseThrow(() ->
        new RuntimeException("Service history not found"));
  }

  @Transactional
  public void addServiceHistory(ServiceHistoryDto serviceHistoryDto, Long userId) {
    User user = userRepository.findById(userId).orElseThrow(EntityExistsException::new);
    ServiceHistory serviceHistory = new ServiceHistory();
    serviceHistory.setDate(serviceHistoryDto.getDate());
    serviceHistory.setType(serviceHistoryDto.getType());
    serviceHistory.setDescription(serviceHistoryDto.getDescription());
    serviceHistory.setUser(user);
    serviceHistory.setVinCode(serviceHistoryDto.getVinCode());
    serviceHistoryRepository.save(serviceHistory);
  }

  @Transactional
  public void updateServiceHistory(Long id, ServiceHistoryDto serviceHistoryDto) {
    ServiceHistory serviceHistory = getServiceHistoryById(id);
    serviceHistoryMapper.updateServiceHistoryInfo(serviceHistoryDto, serviceHistory);
    serviceHistoryRepository.save(serviceHistory);
  }

  @Transactional
  public void deleteServiceHistory(Long id) {
    serviceHistoryRepository.deleteById(id);
  }
}
