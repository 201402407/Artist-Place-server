package com.artiplace.api.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artiplace.api.common.entity.TempProblemEntity;
	
@Repository
public interface TempProblemRepository extends JpaRepository<TempProblemEntity, Integer> {

	List<TempProblemEntity> findByType(int type);
	List<TempProblemEntity> findByState(int state);
	List<TempProblemEntity> findByTypeAndState(int type, int state);
}
 