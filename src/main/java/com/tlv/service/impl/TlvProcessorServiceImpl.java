package com.tlv.service.impl;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import com.tlv.exception.IncorrectTypeException;
import com.tlv.service.TlvProcessorService;
import com.tlv.service.TypeService;
import com.tlv.service.factory.TypeServiceFactory;
import com.tlv.service.factory.WriterServiceFactory;
import com.tlv.util.HelperService;

public class TlvProcessorServiceImpl implements TlvProcessorService {

	private WriterServiceFactory writerServiceFactory;
	private TypeServiceFactory typeServiceFactory;

	public WriterServiceFactory getWriterServiceFactory() {
		return writerServiceFactory;
	}

	public void setWriterServiceFactory(WriterServiceFactory writerServiceFactory) {
		this.writerServiceFactory = writerServiceFactory;
	}

	public TypeServiceFactory getTypeServiceFactory() {
		return typeServiceFactory;
	}

	public void setTypeServiceFactory(TypeServiceFactory typeServiceFactory) {
		this.typeServiceFactory = typeServiceFactory;
	}

	
	public void process(String str) {

		String writer = HelperService.STD_OUT_WRITER;
		try {
			String[] arr = str.split("-");
			if (null != arr && arr.length >= 3) {
				for (int i = 0; i < arr.length;) {
					String type = arr[i];
					int len = Integer.parseInt(arr[i + 1]);
					String output = arr[i + 2];
					if (output.length() > len) {
						arr[i + 2] = output.substring(len);
						output = output.substring(0, len);
						i += 2;
					} else {
						i += 3;
					}
					TypeService service = typeServiceFactory.getTypeService(type);
					if (null != service) {
						output = service.convertToType(output, len);
						writerServiceFactory.getWriterService(writer).write(output);
					} else {
						throw new IncorrectTypeException();
					}
				}
			} else {
				throw new IncorrectTypeException();
			}
		} catch (IncorrectTypeException e) {
			writerServiceFactory.getWriterService(writer).write(HelperService.TYPE_NOT_VALID);

		} catch (NoSuchBeanDefinitionException e) {
			writerServiceFactory.getWriterService(writer).write(HelperService.TYPE_NOT_VALID);
		} catch (Exception e) {
			writerServiceFactory.getWriterService(writer).write(HelperService.TYPE_NOT_VALID);
		}

	}

	}
