package com.sendmsg.msg.Service;


import com.sendmsg.msg.entity.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.annotation.PostConstruct;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

@Service
public class SendMailService {

    @Autowired
    JavaMailSender mailSender;
    //注入默认环境变量
    @Autowired
    Environment env;
    @Autowired
    private SpringTemplateEngine templateEngine;


    /**
     * 发送文本内容
     * @param sendEmail
     * @param tos
     */
    public void sendMessage(SendEmail sendEmail, String[] tos){
        //创建邮件内容
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        //发送人
        simpleMailMessage.setFrom(sendEmail.getForm());

        //收件人
        simpleMailMessage.setTo(tos);
        //邮件内容
        simpleMailMessage.setText(sendEmail.getContent());
         //邮件标题
        simpleMailMessage.setSubject("springboot学会了吗？");
        //发送邮件
        mailSender.send(simpleMailMessage);
}

    /**
     * 发送带附件的邮件
     * @param sendEmail
     * @param tos
     */
    public void sendAttachment(SendEmail sendEmail,String content,String[] tos) throws Exception{
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true,"utf-8");
        //添加发件人
        mimeMessageHelper.setFrom(sendEmail.getForm());
        //添加收件人
        mimeMessageHelper.setTo(tos);
        //添加主题
        mimeMessageHelper.setSubject("带附件发送");
        //添加内容
        mimeMessageHelper.setText(sendEmail.getContent()+content,true);
        //DOTO:带附件发送
        mimeMessageHelper.addAttachment("image.jpg",new File(env.getProperty("mail.file.local")));
        mimeMessageHelper.addAttachment("如果在看你一眼是否还会有感觉的一张图片真的是一张图片.docx",new File(env.getProperty("mail.docx.file.local")));
        mailSender.send(mimeMessage);
    }
    //排除附件的名称校验
    @PostConstruct
    public void init(){
        System.setProperty("mail.mime.splitlongparameters","false");
    }

    /**
     * 渲染模板
     * @param templateFile
     * @param paramMap
     */
    public String renderTemplate(final String templateFile, Map<String,Object> paramMap){
        Context context=new Context(LocaleContextHolder.getLocale());
        //渲染参数
        context.setVariables(paramMap);
        //返回字符串类型的模板
        return templateEngine.process(templateFile,context);
    }
}

