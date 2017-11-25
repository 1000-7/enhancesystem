package org.whusim.enhancesystem.controller;

import com.hankcs.hanlp.HanLP;
import org.ansj.domain.Term;
import org.ansj.recognition.impl.SynonymsRecgnition;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.whusim.enhancesystem.bean.EntityJson;
import org.whusim.enhancesystem.bean.EntityValue;
import org.whusim.enhancesystem.bean.Tag_Attributes;
import org.whusim.enhancesystem.service.EntityService;

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
    //RequestParam(value = "text", required = false, defaultValue = "姚明在上海有个大房子武钢")
    public ResponseEntity<?> findAllEntity(String text) {
        Set<String> expectedNature = new HashSet<String>() {{
            add("nr");
            add("ns");
            add("nt");
            add("nz");
            add("userDefine");
        }};
        //SynonymsRecgnition synonymsRecgnition = new SynonymsRecgnition();
        text = HanLP.convertToSimplifiedChinese(text);
        List<Term> terms = NlpAnalysis.parse(text).getTerms();
        List<EntityJson> entityJsons = new ArrayList<>();
        for (Term term : terms) {
            String word = term.getName(); //拿到词
            String natureStr = term.getNatureStr(); //拿到词性
            if (expectedNature.contains(natureStr)) {
                System.out.println(word);
                try {
//                    if (term.getSynonyms().size()!=0) {
//                        List<String> synonyms = term.getSynonyms();
//                        for (String synonymsWord: synonyms) {
//                            try {
//                                List<EntityValue> entityValue = entityService.findAllByEntity(synonymsWord);
//                                EntityJson entityJson=EntityController.list2json(entityValue);
//                                entityJsons.add(entityJson);
//                            } catch (IndexOutOfBoundsException e) {
//                                continue;
//                            }
//                        }
//                    }
//                    else{
                        List<EntityValue> entityValue = entityService.findAllByEntity(word);
                        EntityJson entityJson=EntityController.list2json(entityValue);
                        entityJsons.add(entityJson);
                    //}
                }catch (IndexOutOfBoundsException e){
                    continue;
                }

            }
        }
        return ResponseEntity.ok(entityJsons);
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
