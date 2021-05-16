package com.artiplace.api.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artiplace.api.common.entity.LoginEntity;
	
@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, String> {

	LoginEntity findByEmailId(String emailId);
}
 