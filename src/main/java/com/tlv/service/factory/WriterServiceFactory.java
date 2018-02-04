package com.tlv.service.factory;

import com.tlv.service.WriterService;

public interface WriterServiceFactory {
	/**
	 * 
	 * Factory method responsible for getting writerServerice.
	 */
	public WriterService getWriterService(String type);
}
