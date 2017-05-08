package com.dealmoon.libs.service;


import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dealmoon.libs.AbstractBase;

@Service
public class Storage extends AbstractBase {
	
	@Value("${config.storage_server}")
	protected String STORAGE_SERVER;

    private String put(String serverUrl, String filename, String data)
	{

        RestTemplate restTemplate = new RestTemplateBuilder()
                .basicAuthorization("avatar", "dealmoon").build();
        String url = serverUrl + "/file/" + filename;
        try {
            RequestEntity<String> requestEntity = new RequestEntity<String>(data, HttpMethod.PUT, new URI(url));
            ResponseEntity<String> result = restTemplate.exchange(requestEntity, String.class);
            StorageResponse rep = getObjectMapper().readValue(result.getBody(), StorageResponse.class);

            if (rep.code == 0) {
                return rep.data;
            }

        } catch (Exception e){
            logger.error("upload failed", e);
        }

		return "";
	}

	public String uploadAvatar(String filename)
	{
		//验证获取第三方绑定帐号头像
		if (!filename.trim().matches(".*(\\.jpg|\\.jpeg|\\.png|\\.gif)$")) {
			return "";
		}
		return put(STORAGE_SERVER,filename, "");
	}

	public String uploadAvatar(String filename, String data)
	{
		return put(STORAGE_SERVER,filename, data);
	}

}
class StorageResponse
{
    public int code = 0;
    public String data = "";
}

