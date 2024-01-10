package com.auto.companion.domain.repository;

import com.auto.companion.carinfo.model.CarInfoDto;
import com.auto.companion.domain.model.CarInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarInfoRepository extends JpaRepository<CarInfo, Long> {

    Page<CarInfo> findAllByUserId(Long userId, Pageable pageable);
}
