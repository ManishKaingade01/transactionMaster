package com.transaction.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transaction.master.entity.MovementCode;
@Repository
public interface MovementCodeRepository extends JpaRepository<MovementCode, Integer> {
	MovementCode findByMovementCode(String movementCode);

}
