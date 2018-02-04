package com.tlv;

import java.util.Scanner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tlv.service.TlvProcessorService;
import com.tlv.service.impl.TlvProcessorServiceImpl;

public class App {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:application-context.xml");
		TlvProcessorService service = applicationContext.getBean(TlvProcessorServiceImpl.class);
		Scanner scanner = null;
		try {
			scanner = new Scanner(System.in);
			while (scanner.hasNext()) {
				service.process(scanner.nextLine());
			}
		} finally {
			if (null != scanner) {
				scanner.close();
			}
			if (null != applicationContext) {
				applicationContext.close();
			}
		}
	}
}
