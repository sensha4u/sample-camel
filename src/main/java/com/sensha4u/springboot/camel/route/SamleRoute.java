package com.sensha4u.springboot.camel.route;

import java.util.Arrays;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.sensha4u.springboot.camel.processor.FileProcessor;

@Component
public class SamleRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		// TODO Auto-generated method stub
		
		// moveAllFile();
		// moveSpecificFile("2.txt");
		// moveAllFileByContent("LOWER");
		//convertFiletoCSV();
		//splitFileByStatus();
		
	}

	private void moveAllFile() {
		from("file://src/test/source-folder?noop=true")
			.process(new FileProcessor())
			.to("file://src/test/destination-folder");
	}

	private void moveSpecificFile(String type) {
		from("file://src/test/source-folder?noop=true")
			.process(new FileProcessor())
			.filter(header(Exchange.FILE_NAME)
			.endsWith(type))
			.to("file://src/test/destination-folder");
	}

	private void moveAllFileByContent(String content) {
		from("file://src/test/source-folder?noop=true")
			.process(new FileProcessor())
			.filter(body().contains(content))
			.to("file://src/test/destination-folder");
	}

	private void convertFiletoCSV() {
		from("file://src/test/source-folder?noop=true")
	        .choice().when(simple("${file:ext} == 'txt'"))
			.process(new FileProcessor())
			.process(
					p -> {
							String originalBody = p.getIn().getBody(String.class);
							StringBuilder sb = new StringBuilder();
							Arrays.stream(originalBody.split(" ")).forEach(s -> {sb.append(s + ",");});
							p.getIn().setBody(sb);
						}
					)
			.to("file://src/test/destination-folder");
	}

	private void splitFileByStatus() {
		from("file://src/test/source-folder?noop=true")
	      	.choice()
	      	.when(simple("${file:ext} == 'csv'"))
		  	 	.unmarshal().csv().split(body().tokenize(","))
		  	 	.choice()
		  	 		.when(body()
		  	 			.contains("status1"))
		  	 			.to("file://src/test/destination-folder?filename=Status-1.csv")
					.when(body()
						.contains("status2"))
						.to("file://src/test/destination-folder?filename=Status-2.csv")
					.when(body()
						.contains("status3"))
						.to("file://src/test/destination-folder?filename=Status-3.csv");
	}

}
