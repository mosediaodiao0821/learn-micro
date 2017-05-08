package com.dealmoon.libs.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dealmoon.libs.AbstractBase;

@Service
public class Mail extends AbstractBase {

    @Value("${config.send_mail_url}")
    public String SEND_MAIL_URL;

    public boolean send(String fromName, String email, String subject, String message)
    {
        RestTemplate template = new RestTemplate();
        Map<String, String> paremeter = new HashMap<>();
        paremeter.put("source", "learn");
        paremeter.put("from", fromName+"<noreply@learn.com>");
        paremeter.put("to", email);
        paremeter.put("subject", subject);
        paremeter.put("message", message);

        //异步发送邮件
        new Thread(){
            @Override
            public void run() {
                super.run();
                // 发送验证邮件
                try {
                    Result result = template.postForObject(SEND_MAIL_URL, paremeter, Result.class);
                    // log
                } catch (Exception e) {
                    // log
                }
            }
        }.start();

        return true;
    }

    class Result
    {
        public String status = "";
        public String message = "";
    }
}





