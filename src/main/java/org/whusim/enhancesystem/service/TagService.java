package org.whusim.enhancesystem.service;

import org.springframework.stereotype.Service;

import org.whusim.enhancesystem.bean.Tag;
import org.whusim.enhancesystem.repository.TagRepository;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class TagService {
    @Resource
    private TagRepository tagRepository;
    @Transactional
    public Iterable<Tag> getall(){
        return tagRepository.findAll();
    }
    @Transactional
    public List<Tag> findtags(int entityid){
        return tagRepository.findAllByEntityid(entityid);
    }
    @Transactional
    public Tag save(Tag tag){
        return tagRepository.save(tag);

    }
}
