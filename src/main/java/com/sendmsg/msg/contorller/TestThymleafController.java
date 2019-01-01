package com.sendmsg.msg.contorller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/")
@Controller
public class TestThymleafController {

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public Object getIndex(){
        return "index";
    }
}
