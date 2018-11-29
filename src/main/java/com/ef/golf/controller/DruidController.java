package com.ef.golf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class DruidController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Object index(){
        ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("redirect:/druid/index.html");
       return modelAndView;
    }
}
