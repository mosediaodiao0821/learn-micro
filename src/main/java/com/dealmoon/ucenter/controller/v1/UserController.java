package com.dealmoon.ucenter.controller.v1;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dealmoon.ucenter.controller.AbstractController;
import com.dealmoon.ucenter.models.SdkResult;

@RestController
@RequestMapping("/v1")
public class UserController extends AbstractController {
	
	
	@RequestMapping("/user/info")
	public Map<String,Object> info(@RequestParam Map<String,String> req){
		Map<String,Object> data = new HashMap<String,Object>();
		SdkResult result = new SdkResult(req.get("lang"));
		data.put("result",result);
		data.put("userInfo","");
		return data;
	}
	
}
