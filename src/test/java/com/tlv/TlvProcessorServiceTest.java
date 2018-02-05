package com.tlv;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tlv.service.TlvProcessorService;
import com.tlv.service.impl.TlvProcessorServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
public class TlvProcessorServiceTest {

        @Autowired
	private TlvProcessorService tlvProcessorService;

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void load() {
		System.setOut(new PrintStream(outContent));
	}

	@Test
	public void testprocess1() {
		tlvProcessorService.process("UPPRCS-0005-abcde");
		assertTrue("UPPRCS-ABCDE".equalsIgnoreCase(outContent.toString().trim()));

	}

	@Test
	public void testprocess2() {
		tlvProcessorService.process("REPLCE-0003-123");
		assertTrue("REPLCE-THIS STRING".equalsIgnoreCase(outContent.toString().trim()));

	}

	@Test
	public void testprocess3() {
		tlvProcessorService.process("TAG001-0012-abcdefgh1234");
		assertTrue("Type not valid".equalsIgnoreCase(outContent.toString().trim()));

	}

	@Test
	public void testprocess4() {
		tlvProcessorService.process("UPPRCS-0008-AbcdefghREPLCE-0003-123REPLCE-0001-Z");
		String output = "UPPRCS-ABCDEFGH\nREPLCE-THIS STRING\nREPLCE-THIS STRING";
		assertTrue(output.equalsIgnoreCase(outContent.toString().trim()));

	}
}
