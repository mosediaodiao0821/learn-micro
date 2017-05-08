package com.dealmoon.ucenter.verifycode;

import java.awt.image.BufferedImage;

public abstract class AbstractCaptchaService{

	protected RandomFontFactory fontFactory;
	protected RandomWordFactory wordFactory;
	protected ColorFactory colorFactory;
	protected BackgroundFactory backgroundFactory;
	protected AbstractTextRenderer textRenderer;
	protected AbstractFilterFactory filterFactory;
	protected int width;
	protected int height;

	public void setFontFactory(RandomFontFactory fontFactory) {
		this.fontFactory = fontFactory;
	}

	public void setWordFactory(RandomWordFactory wordFactory) {
		this.wordFactory = wordFactory;
	}

	public void setColorFactory(ColorFactory colorFactory) {
		this.colorFactory = colorFactory;
	}

	public void setBackgroundFactory(BackgroundFactory backgroundFactory) {
		this.backgroundFactory = backgroundFactory;
	}

	public void setTextRenderer(AbstractTextRenderer textRenderer) {
		this.textRenderer = textRenderer;
	}

	public void setFilterFactory(AbstractFilterFactory filterFactory) {
		this.filterFactory = filterFactory;
	}

	public RandomFontFactory getFontFactory() {
		return fontFactory;
	}

	public RandomWordFactory getWordFactory() {
		return wordFactory;
	}

	public ColorFactory getColorFactory() {
		return colorFactory;
	}

	public BackgroundFactory getBackgroundFactory() {
		return backgroundFactory;
	}

	public AbstractTextRenderer getTextRenderer() {
		return textRenderer;
	}

	public AbstractFilterFactory getFilterFactory() {
		return filterFactory;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Captcha getCaptcha() {
		BufferedImage bufImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		backgroundFactory.fillBackground(bufImage);
		String word = wordFactory.getNextWord();
		textRenderer.draw(word, bufImage, fontFactory, colorFactory);
		bufImage = filterFactory.applyFilters(bufImage);
		return new Captcha(word, bufImage);
	}

}
