package org.whusim.enhancesystem.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.whusim.enhancesystem.bean.Mention;
import org.whusim.enhancesystem.service.MentionService;

import javax.annotation.Resource;

@RestController
@RequestMapping(path = "/mention")
public class MentionController {
    @Resource
    private MentionService mentionService;
    @RequestMapping(path = "/add")
    public Mention addentity(@RequestParam(value = "mentionvalue", required = false, defaultValue = "1") String mentionvalue) {
        Mention mention =new Mention(mentionvalue);

        return mentionService.save(mention);
    }
}
