package com.artiplace.api.common.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artiplace.api.common.entity.LoginLogEntity;
	
@Repository
public interface LoginLogRepository extends JpaRepository<LoginLogEntity, BigInteger> {
}
 