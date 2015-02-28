package com.achui.quick.freemarker;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

public class CustomFreeMarkerViewResolver extends FreeMarkerViewResolver {
	public CustomFreeMarkerViewResolver() {
		setViewClass(requiredViewClass());
	}
	/**
	 * Requires {@link FreeMarkerView}.
	 */
	@Override
	protected Class requiredViewClass() {
		return CustomFreemarkerView.class;
	}
}
