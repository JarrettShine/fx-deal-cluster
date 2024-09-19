package com.demo.repository;

import com.demo.entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DealRepository extends JpaRepository<Deal, Long> {
    boolean existsByUniqueId(String uniqueId);
}
