package com.artiplace.api.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artiplace.api.common.entity.RegistNicknameEntity;
	
@Repository
public interface RegistNicknameRepository extends JpaRepository<RegistNicknameEntity, String> {
}
 