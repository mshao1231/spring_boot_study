package com.aku.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*专门用于处理页面响应的控制器*/
@Controller
public class PageController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}