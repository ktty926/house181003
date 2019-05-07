package com.jk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("page")
public class LoginController {

    @RequestMapping("aaa")
    public  String aaa(){
        return "login";
    }

    @RequestMapping("login")
    public String login(){
        return "loginsj";
    }
    @RequestMapping("youx")
    public String youx(){
        return "loginyx";
    }
    @RequestMapping("addList")
    public String addList(){
        return "addList";
    }

}
