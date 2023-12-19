package com.diegobarrioh.api.akdmiaapi.domain.repository;

import com.diegobarrioh.api.akdmiaapi.domain.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {
}