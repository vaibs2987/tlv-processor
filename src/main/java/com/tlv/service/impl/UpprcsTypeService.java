package com.tlv.service.impl;

import com.tlv.service.TypeService;

public class UpprcsTypeService implements TypeService {

	private static final String NAME = "UPPRCS-";

	public String convertToType(String str, int len) {
		StringBuilder builder = new StringBuilder(NAME);
		builder.append(str.toString().substring(0, len).toUpperCase());
		return builder.toString();
	}

}
