package org.whusim.enhancesystem.controller;

import com.hankcs.hanlp.HanLP;
import org.ansj.domain.Term;
import org.ansj.recognition.impl.SynonymsRecgnition;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;
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



    @RequestMapping(path = "/findall")
    public ModelAndView findAllEntity(@RequestParam(value = "text", required = false, defaultValue = "姚明在上海有个大房子") String text) {
        Set<String> expectedNature = new HashSet<String>() {{
            add("nr");
            add("ns");
            add("nt");
            add("nz");
            add("userDefine");
        }};
        SynonymsRecgnition synonymsRecgnition = new SynonymsRecgnition();
        text = HanLP.convertToSimplifiedChinese(text);
        //List<Term> terms = NlpAnalysis.parse(text).getTerms();
        List<EntityJson> entityJsons = new ArrayList<>();
        for (Term term : NlpAnalysis.parse(text).recognition(synonymsRecgnition)) {
            String word = term.getName(); //拿到词
            String natureStr = term.getNatureStr(); //拿到词性
            if (expectedNature.contains(natureStr)) {
                System.out.println(word);
                try {
                    if (term.getSynonyms().size()!=0) {
                        List<String> synonyms = term.getSynonyms();
                        for (String synonymsWord: synonyms) {
                            List<EntityValue> entityValue = entityService.findAllByEntity(synonymsWord);
                            EntityJson entityJson=EntityController.list2json(entityValue);
                            entityJsons.add(entityJson);
                        }
                    }
                    else{
                        List<EntityValue> entityValue = entityService.findAllByEntity(word);
                        EntityJson entityJson=EntityController.list2json(entityValue);
                        entityJsons.add(entityJson);
                    }
                }catch (IndexOutOfBoundsException e){
                    continue;
                }

            }
        }
        return new ModelAndView("index", "entityjsons", entityJsons);
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
