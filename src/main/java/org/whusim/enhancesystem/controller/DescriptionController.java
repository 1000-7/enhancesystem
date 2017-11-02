package org.whusim.enhancesystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.whusim.enhancesystem.service.DescriptionService;

import javax.annotation.Resource;

@Controller
public class DescriptionController {
    @Resource
    private DescriptionService descriptionService;

}
