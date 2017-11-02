package org.whusim.enhancesystem.service;

import org.springframework.stereotype.Service;
import org.whusim.enhancesystem.bean.EntityValue;
import org.whusim.enhancesystem.repository.EntityRepository;

import javax.annotation.Resource;
import javax.transaction.Transactional;


@Service
public class EntityService {
//    @PersistenceContext
//    private EntityManager em;

    @Resource
    private EntityRepository entityRepository;

    @Transactional
    public Iterable<EntityValue> findEntityValue() {
        return entityRepository.findAll();
    }
    @Transactional
    public Iterable<EntityValue> findEntityValuesByMentionid(int mentionid) {
        return entityRepository.findEntityValuesByMentionid(mentionid);
    }
    @Transactional
    public EntityValue findOneentity(int i) {
        return entityRepository.findEntityValueByEntityid(i);
    }

    @Transactional
    public EntityValue save(EntityValue en) {
        return entityRepository.save(en);
    }

}
