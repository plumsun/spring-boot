package com.study.repository;

import com.study.entity.ClBizApplyEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClBizApplyDao extends CrudRepository<ClBizApplyEntity, Long> {

    @Query("select c from ClBizApplyEntity c where c.pkClbizapply = ?1")
    Optional<ClBizApplyEntity> getById(Long id);

    @Query("select c from ClBizApplyEntity c where c.shipId = ?1 and c.deleteFlag = '0'")
    Optional<ClBizApplyEntity> getById(String shipId);
}
