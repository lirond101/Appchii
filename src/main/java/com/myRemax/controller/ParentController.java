package com.myRemax.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.HandlerMapping;

public class ParentController {

	@ModelAttribute
	public void tagController(HttpServletRequest request) {
		Set<MediaType> supportedMediaTypes = new HashSet<MediaType>();
		supportedMediaTypes.add(MediaType.APPLICATION_JSON);
		request.setAttribute(HandlerMapping.PRODUCIBLE_MEDIA_TYPES_ATTRIBUTE,
				supportedMediaTypes);
	}
}
