package com.sendmsg.msg.contorller;

import com.sendmsg.msg.Service.SendMailService;
import com.sendmsg.msg.entity.SendEmail;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class SendMsgController {


    @Autowired
    Environment env;
    @Resource
    SendMailService sendMailService;
    @RequestMapping(value = "/sendMsg",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object test(@RequestBody SendEmail sendEmail){
        //TODO:未完成正确request返回形式。
        try {
            String[] tos = StringUtils.split(sendEmail.getSend(), ",");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("content","模板的渲染");
            map.put("lucy","露西");
            String html = sendMailService.renderTemplate(env.getProperty("mail.template.location"), map);
            //mail.template.location
            sendMailService.sendAttachment(sendEmail,html, tos);

        }catch (Exception e){
            e.printStackTrace();
        }
        return "成功";
    }
}
