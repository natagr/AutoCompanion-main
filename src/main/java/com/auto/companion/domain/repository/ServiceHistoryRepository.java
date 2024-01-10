package com.auto.companion.domain.repository;

import com.auto.companion.domain.model.ServiceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceHistoryRepository extends JpaRepository<ServiceHistory, Long> {

    List<ServiceHistory> findByUserIdAndVinCode(Long userId, String vinCode);

}
