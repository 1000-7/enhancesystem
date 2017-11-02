package org.whusim.enhancesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.whusim.enhancesystem.bean.EntityValue;

import java.util.List;

public interface EntityRepository extends JpaRepository<EntityValue,Integer> {
    List<EntityValue> findEntityValuesByMentionid(int mentionid);
    EntityValue findEntityValueByEntityid(int entityid);
}
