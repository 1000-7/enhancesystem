package org.whusim.enhancesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;
import org.whusim.enhancesystem.bean.EntityValue;

import java.util.List;


public interface EntityRepository extends JpaRepository<EntityValue,Integer> {
    List<EntityValue> findAllByEntity(String entity);
    EntityValue findEntityValueById(int id);
    void deleteByEntityAndTag(String entity, String tag);
    void deleteByEntity(String entity);
    @Modifying
    @Transactional
    @Query("update EntityValue ev set ev.attribute=?3 where ev.entity=?1 and ev .tag=?2")
    int updateEntity(String entity, String tag, String attribute);

    @Modifying
    @Transactional
    @Query("update EntityValue ev set ev.attribute=?4 where ev.entity=?1 and ev .tag=?2 and ev .attribute=?3")
    int updateEntityAttribute(String entity, String tag, String oldattribute,String newattribute);
}
