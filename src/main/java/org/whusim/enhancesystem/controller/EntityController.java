package org.whusim.enhancesystem.controller;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;
import org.whusim.enhancesystem.bean.*;
import org.whusim.enhancesystem.service.EntityService;
import org.whusim.enhancesystem.service.MentionService;
import org.whusim.enhancesystem.service.TagService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class EntityController {
    @Resource
    private EntityService entityService;

    @RequestMapping(path = "/entity/add")
    public EntityValue addentity(@RequestParam(value = "entity", required = false, defaultValue = "1") String entity
            , @RequestParam(value = "tag", required = false, defaultValue = "1") String tag
            , @RequestParam(value = "attribute", required = false, defaultValue = "1") String attribute) {

        EntityValue entityValue = new EntityValue(entity, tag, attribute);
        return entityService.save(entityValue);
    }



    @RequestMapping(path = "/entity/findall")
    public List<EntityJson> findAllEntity(@RequestParam(value = "text", required = false, defaultValue = "姚明在上海有个大房子") String text) {
        Set<String> expectedNature = new HashSet<String>() {{
            add("nr");
            add("ns");
            add("nt");
            add("nz");
            add("userDefine");
        }};
        List<Term> terms = NlpAnalysis.parse(text).getTerms();
        List<EntityJson> entityJsons = new ArrayList<>();
        for (Term term : terms) {
            String word = term.getName(); //拿到词
            String natureStr = term.getNatureStr(); //拿到词性
            if (expectedNature.contains(natureStr)) {
                System.out.println(word);
                try {
                    List<EntityValue> entityValue = entityService.findAllByEntity(word);
                    EntityJson entityJson=EntityController.list2json(entityValue);
                    entityJsons.add(entityJson);
                }catch (IndexOutOfBoundsException e){
                    continue;
                }

            }
        }
        return entityJsons;
    }



    public static EntityJson list2json(List<EntityValue> entityValues){
        EntityJson entityJson = new EntityJson();
        String entity=entityValues.get(0).getEntity();
        List<Tag_Attributes>attributes=new ArrayList<>();
        for (EntityValue entityValue:entityValues){
            Tag_Attributes attribute=new Tag_Attributes(entityValue.getTag(),entityValue.getAttribute());
            attributes.add(attribute);
        }
        entityJson.setEntity(entity);
        entityJson.setAttributes(attributes);
        return entityJson;
    }
}
