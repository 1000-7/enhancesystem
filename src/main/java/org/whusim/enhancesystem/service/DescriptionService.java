package org.whusim.enhancesystem.service;

import org.springframework.stereotype.Service;
import org.whusim.enhancesystem.bean.Description;
import org.whusim.enhancesystem.repository.DescriptionRepository;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
public class DescriptionService {
    @Resource
    private DescriptionRepository descriptionRepository;
    @Transactional
    public Iterable<Description> findAll(){
        return descriptionRepository.findAll();
    }

}
