package com.artiplace.api.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artiplace.api.common.entity.MembersEntity;
	
@Repository
public interface MembersRepository extends JpaRepository<MembersEntity, String> {

	MembersEntity getOneByEmailId(String emailId);
	MembersEntity findByEmailId(String emailId);
	boolean existsByEmailId(String emailId);
}
 