package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginControl {

    ModelAndView mv = new ModelAndView();

    @GetMapping("/login")
    public ModelAndView login() {
        mv.setViewName("login");
        return mv;
    }

    @GetMapping("/")
    public ModelAndView getIndex() {
        mv.setViewName("index");
        return mv;
    }

    @GetMapping("/producto1")
    public ModelAndView getAries() {
        mv.setViewName("producto1");
        return mv;
    }

    @GetMapping("/producto2")
    public ModelAndView getTauro() {
        mv.setViewName("producto2");
        return mv;
    }

    @GetMapping("/producto3")
    public ModelAndView getGéminis() {
        mv.setViewName("producto3");
        return mv;
    }

}
