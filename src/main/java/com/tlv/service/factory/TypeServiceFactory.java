package com.tlv.service.factory;

import com.tlv.service.TypeService;

public interface TypeServiceFactory {

	/**
	 * 
	 * Factory method responsible for getting typeServerice.
	 */
	public TypeService getTypeService(String type);
}
