package org.whusim.enhancesystem.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.whusim.enhancesystem.bean.Tag;
import org.whusim.enhancesystem.repository.TagRepository;
import org.whusim.enhancesystem.service.TagService;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@RestController
@RequestMapping(path = "/tag")

public class TagController {
    @Resource
    private TagService tagService;

    @RequestMapping(path = "/tag/add")
    public Tag add(@RequestParam(value = "entityid", required = false, defaultValue = "1") String entityid
            , @RequestParam(value = "tagname", required = false, defaultValue = "1") String tagname
            , @RequestParam(value = "tagvalue", required = false, defaultValue = "1") String tagvalue){
        Tag tag =new Tag(new Integer(entityid),tagname,tagvalue);
        return tagService.save(tag);
    }


}
