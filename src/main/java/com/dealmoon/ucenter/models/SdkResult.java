package com.dealmoon.ucenter.models;

import com.dealmoon.ucenter.constant.Common;


public class SdkResult {
	private int code = 0;
	public String message = "success";
	public String lang;
	
	public SdkResult(String lang) {
		super();
		this.lang = lang;
	}

	public SdkResult() {
		super();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
		setTips();
	}

	public void setTips()
	{
		if (code == 0) {
			return;
		}
		String cnTip = "";
		String enTip = "";

		switch (code) {
			case 1:
				cnTip = "用户名已存在";
				enTip = "Username occupied";
				break;
			case 2:
				cnTip = "此Email已被注册";
				enTip = "Email occupied";
				break;
			default:
				break;
		}

		message = enTip;

		if (Common.LANG_CN.equals(this.lang)) {
			message = cnTip;
		}

		if (code != -1) {
			// 输出ios错误号
			code = 1000 + code;
		}
	}
	
}
