package com.chat.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/")
public class Controllers {
    @GetMapping("")
    public String indexGET(){
        log.info("@Controller, index GET()");
        return "index";
    }
}
