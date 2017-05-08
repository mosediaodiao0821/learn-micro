package com.dealmoon.ucenter.controller.v2;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("UserController2")
@RequestMapping("/v2")
public class UserController extends com.dealmoon.ucenter.controller.v1.UserController {

    // 测试高版本向下兼容
    @RequestMapping("/user/info")
    public Map<String,Object> info(@RequestParam Map<String,String> req){
            Map<String,Object> data = new HashMap<String,Object>();
        System.out.println(storageService.uploadAvatar("http://7lrwf5.com1.z0.glb.clouddn.com/image/Happy_retirement_Google_Reader.png"));

        return data;
    }
}
