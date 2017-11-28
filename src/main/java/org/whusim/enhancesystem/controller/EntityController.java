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

    @RequestMapping(path = "/entity/deleteonetag")
    public void deleteByTagAndEntity(@RequestParam(value = "entity", required = false, defaultValue = "姚明") String entity
            , @RequestParam(value = "tag", required = false, defaultValue = "1") String tag) {

        //EntityValue entityValue = new EntityValue(entity, tag);
        entityService.deleteByTagAndEntity(entity, tag);
    }

    @RequestMapping(path = "/entity/deleteoneentity")
    public void deleteByEntity(@RequestParam(value = "entity", required = false, defaultValue = "1") String entity) {

        //EntityValue entityValue = new EntityValue(entity, tag, attribute);
        entityService.deleteByEntity(entity);
    }

    @RequestMapping(path = "/entity/updateentity")
    public void updateentity(@RequestParam(value = "entity", required = false, defaultValue = "1") String entity
            , @RequestParam(value = "tag", required = false, defaultValue = "1") String tag
            , @RequestParam(value = "attribute", required = false, defaultValue = "1") String attribute) {

        //EntityValue entityValue = new EntityValue(entity, tag, attribute);
        entityService.update(entity, tag, attribute);
    }

    @RequestMapping(path = "/entity/updateattribute")
    public void updateentityattribute(@RequestParam(value = "entity", required = false, defaultValue = "苹果公司") String entity
            , @RequestParam(value = "tag", required = false, defaultValue = "d sa d") String tag
            , @RequestParam(value = "attribute", required = false, defaultValue = "dasada d s") String oldattribute,
                             @RequestParam(value = "attribute", required = false, defaultValue = "1") String newattribute) {

        //EntityValue entityValue = new EntityValue(entity, tag, attribute);
        entityService.update(entity, tag, oldattribute, newattribute);
    }

    @RequestMapping(path = "/entity/select")
    public List<EntityValue> selectentity(@RequestParam(value = "entity", required = false, defaultValue = "1") String entity
            , @RequestParam(value = "tag", required = false, defaultValue = "1") String tag
            , @RequestParam(value = "attribute", required = false, defaultValue = "1") String attribute) {

        EntityValue entityValue = new EntityValue(entity, tag, attribute);
        return entityService.findAllByEntity(entity);
    }



    @RequestMapping(path = "/entity/findall")
    //RequestParam(value = "text", required = false, defaultValue = "姚明在上海有个大房子武钢")
    public ResponseEntity<?> findAllEntity(String text) {
        Set<String> expectedNature = new HashSet<String>() {{
            add("nr");
            add("ns");
            add("nt");
            add("n");
            //add("nz");
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
                    List<EntityValue> entityValue = entityService.findAllByEntity(word);
                    if(entityValue.isEmpty()){

                        List<String> synonyms = term.getSynonyms();
                        if(synonyms==null){
                            continue;
                        }
                        for (String synonymsWord: synonyms) {
                            entityValue = entityService.findAllByEntity(synonymsWord);
                            if(entityValue.isEmpty()){
                                continue;
                            }
                            EntityJson entityJson=EntityController.list2json(word, entityValue);
                            entityJsons.add(entityJson);
                            break;
                        }
                    }
                    else {
                        EntityJson entityJson = EntityController.list2json(word, entityValue);
                        entityJsons.add(entityJson);
                    }
                }catch (IndexOutOfBoundsException e){
                    continue;
                }

            }
        }
        return ResponseEntity.ok(entityJsons);
    }



    public static EntityJson list2json(String word,List<EntityValue> entityValues){
        EntityJson entityJson = new EntityJson();
        //String entity=entityValues.get(0).getEntity();
        String entity = word;
        List<Tag_Attributes>attributes=new ArrayList<>();
        for (EntityValue entityValue:entityValues){
            String attribute_modify = entityValue.getAttribute().replaceAll("<a>","").replaceAll("</a>","");
            Tag_Attributes attribute=new Tag_Attributes(entityValue.getTag(),attribute_modify);
            attributes.add(attribute);
        }
        entityJson.setEntity(entity);
        entityJson.setAttributes(attributes);
        return entityJson;
    }
}
