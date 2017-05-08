package com.dealmoon.ucenter.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.dealmoon.libs.codec.MD5;

@Component
public class ThumbNail {

	public String thumbServer="https://imgcache.learn.com";
	public String key = "&6#learn.magic.GG";
	@Value("${config.img_url}")
	private String IMG_URL;

	public String getUrl(String url, String size, String type)
	{
		
		if (url == null || url.equals("")) {
			return url;
		}
		if(url.startsWith(thumbServer)){
			String[] tmp = url.split("\\.");
			String ext = "jpg";
			if (tmp.length > 1) {
				ext = tmp[tmp.length-1];
			}
			url = url.replace(thumbServer+"/", "");
			url = url.replaceAll(ext + "_.+" + ext + "$", ext + "");
			url = "http://"+url;
		} else if(!url.startsWith("http")) {
			url = IMG_URL + url;
		}
		String[] tmp = url.split("\\.");
		String ext = "jpg";
		if (tmp.length > 1) {
			ext = tmp[tmp.length-1];
		}
		switch (ext.toLowerCase()) {
			case "jpg":
			case "jpeg":
			case "gif":
			case "png":
				url = url.replaceAll("(?i)https?://", "");
				String md5Key = MD5.getMD5(key+url+"_"+size+"_"+type).substring(0,4);
				return thumbServer+"/"+url+"_"+size+"_"+type+"_"+md5Key+"."+ext;
			default:
				if(ext.indexOf("jpeg")>-1){
					url = url.replaceAll("(?i)https?://", "");
					String md5Key1 = MD5.getMD5(key+url+"_"+size+"_"+type).substring(0,4);
					return thumbServer+"/"+url+"_"+size+"_"+type+"_"+md5Key1+"."+ext;
				}
				return url;
		}
	}

	public String getImgByScale(String scale, String url)
	{
		
		String size = scale+"00";
		size = size + "_" + size;

		return getUrl(url, size, "2");
	}
}
