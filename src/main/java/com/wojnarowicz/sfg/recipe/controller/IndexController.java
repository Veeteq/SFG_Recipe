package com.wojnarowicz.sfg.recipe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class IndexController {

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String getIndexPage() {
        log.debug("IndexController: getIndexPage");
        return "index";
    }
}
