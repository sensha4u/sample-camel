package com.sensha4u.springboot.camel.processor;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class FileProcessor implements Processor {

	public void process(Exchange exchange) throws Exception {

		String originalFileName = (String) exchange.getIn().getHeader(Exchange.FILE_NAME, String.class);
		String originalBody = (String) exchange.getIn().getBody(String.class);

		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");

		exchange.getIn().setBody(originalBody.toLowerCase());
		exchange.getIn().setHeader(Exchange.FILE_NAME, (dateFormat.format(date) + "-" + originalFileName).replace( ".txt", ".csv"));	
		
	}

}