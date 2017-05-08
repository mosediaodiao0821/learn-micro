package com.dealmoon.ucenter.verifycode;

import java.awt.image.BufferedImageOp;
import java.util.ArrayList;
import java.util.List;

public class CurvesRippleFilterFactory extends RippleFilterFactory {

	protected CurvesImageOp curves = new CurvesImageOp();

	public CurvesRippleFilterFactory() {
	}

	public CurvesRippleFilterFactory(ColorFactory colorFactory) {
		setColorFactory(colorFactory);
	}

	@Override
	protected List<BufferedImageOp> getPreRippleFilters() {
		List<BufferedImageOp> list = new ArrayList<BufferedImageOp>();
		list.add(curves);
		return list;
	}

	public void setStrokeMin(int strokeMin) {
		curves.setStrokeMin(strokeMin);
	}

	public void setStrokeMax(int strokeMax) {
		curves.setStrokeMax(strokeMax);
	}

	public void setColorFactory(ColorFactory colorFactory) {
		curves.setColorFactory(colorFactory);
	}

}
