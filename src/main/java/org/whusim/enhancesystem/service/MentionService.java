package org.whusim.enhancesystem.service;

import org.springframework.stereotype.Service;
import org.whusim.enhancesystem.bean.Mention;
import org.whusim.enhancesystem.repository.MentionRepository;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
public class MentionService {
    @Resource
    private MentionRepository mentionRepository;
    @Transactional
    public Mention save(Mention en) {
        return mentionRepository.save(en);
    }
    @Transactional
    public Iterable<Mention> findAll(){
        return mentionRepository.findAll();
    }
    @Transactional
    public Mention findOne(int i){
        return mentionRepository.findOne(i);
    }
    @Transactional
    public Mention findByMentionvalue(String mentionvalue){
        System.out.println(mentionvalue);
        Mention mention=mentionRepository.findByMentionvalue(mentionvalue);
        System.out.println(mention.getMentionvalue());
        return mentionRepository.findByMentionvalue(mentionvalue);
    }
}
