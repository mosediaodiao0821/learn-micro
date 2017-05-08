package com.dealmoon.ucenter.verifycode;

import java.awt.Color;
import java.io.IOException;

public class GenerateVerifyCode {
	
	public static Captcha Generate(String email) throws IOException {
		ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
		cs.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));

		CurvesRippleFilterFactory crff = new CurvesRippleFilterFactory(cs.getColorFactory());
		crff.setStrokeMin(1);
		crff.setStrokeMax(2);
		cs.setFilterFactory(crff);

		RandomFontFactory rff = new RandomFontFactory();
		rff.setMinSize(20);
		rff.setMaxSize(25);
		cs.setFontFactory(rff);

		cs.setWidth(75);
		cs.setHeight(25);

		RandomWordFactory arwf = new RandomWordFactory();
		arwf.setMinLength(4);
		arwf.setMaxLength(4);
		cs.setWordFactory(arwf);

		return cs.getCaptcha();
	}
}
