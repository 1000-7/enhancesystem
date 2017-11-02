package org.whusim.enhancesystem.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.whusim.enhancesystem.bean.EntityValue;
import org.whusim.enhancesystem.bean.Mention;
import org.whusim.enhancesystem.bean.Tag;
import org.whusim.enhancesystem.service.EntityService;
import org.whusim.enhancesystem.service.MentionService;
import org.whusim.enhancesystem.service.TagService;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class EntityController {
    @Resource
    private EntityService entityService;
    @Resource
    private MentionService mentionService;
    @Resource
    private TagService tagService;
    @RequestMapping(path = "/entity/add")
    public EntityValue addentity(@RequestParam(value = "entity", required = false, defaultValue = "1") String entity
            , @RequestParam(value = "description", required = false, defaultValue = "1") String description
            , @RequestParam(value = "mentionid", required = false, defaultValue = "1") String mentionid) {
        int mentionno = new Integer(mentionid);
        EntityValue entityValue = new EntityValue(mentionno, entity, description);
        return entityService.save(entityValue);
    }

    @RequestMapping(path = "/mention2entity/find")
    public Iterable<EntityValue> findentity(@RequestParam(value = "mention", required = false, defaultValue = "南京") String mention) {
        //System.out.println(mention);
        Mention mentionBean = mentionService.findByMentionvalue(mention);
        int i = mentionBean.getMentionid();
        System.out.println(i);
        Iterable<EntityValue> entityValue = entityService.findEntityValuesByMentionid(i);
        return entityValue;
    }
    @RequestMapping(path = "/entity2desc/find")
    public EntityValue findoneentity(@RequestParam(value = "entityid",required = false,defaultValue = "1")String entityid){
        EntityValue entityValue=entityService.findOneentity(new Integer(entityid));
        return entityValue;
    }
    @RequestMapping(path = "/entity2desc/find/tag")
    public List<Tag> findtags(@RequestParam(value = "entityid",required = false,defaultValue = "1")String entityid){
        List<Tag> tags=tagService.findtags(new Integer(entityid));
        return tags;
    }
}
