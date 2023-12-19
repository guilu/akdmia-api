package com.diegobarrioh.api.akdmiaapi.domain.repository;

import com.diegobarrioh.api.akdmiaapi.domain.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}