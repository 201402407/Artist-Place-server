package com.artiplace.api.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artiplace.api.common.entity.LoginEntity;
import com.artiplace.api.common.pvo.LoginPVO;
	
@Repository
public interface LoginRepository extends JpaRepository<LoginPVO, String> {

	LoginEntity findByEmailId(String emailId);
}
 