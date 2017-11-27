package org.whusim.enhancesystem.service;

import org.springframework.stereotype.Service;
import org.whusim.enhancesystem.bean.EntityValue;
import org.whusim.enhancesystem.repository.EntityRepository;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;


@Service
public class EntityService {

    @Resource
    private EntityRepository entityRepository;

    @Transactional
    public Iterable<EntityValue> findEntityValue() {
        return entityRepository.findAll();
    }
    @Transactional
    public List<EntityValue> findAllByEntity(String entity) {
        return entityRepository.findAllByEntity(entity);
    }
    @Transactional
    public EntityValue findOneentity(int id) {
        return entityRepository.findEntityValueById(id);
    }

    @Transactional
    public EntityValue save(EntityValue en) {
        return entityRepository.save(en);
    }

    @Transactional
    public void deleteByTagAndEntity(String entity, String tag){
        entityRepository.deleteByEntityAndTag(entity, tag);
    }

    @Transactional
    public void deleteByEntity(String entity){
        entityRepository.deleteByEntity(entity);
    }

    @Transactional
    public void update(String entity, String tag, String attribute){
        entityRepository.updateEntity(entity, tag, attribute );
    }

    @Transactional
    public void update(String entity, String tag, String oldattribute, String newattribute){
        entityRepository.updateEntityAttribute(entity, tag, oldattribute, newattribute );
    }

}
