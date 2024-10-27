package com.in28minutes.microservices.camel_microservice_a.routes.a.activemq;

import java.time.LocalDateTime;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// @Component
public class MyFirstTimerRouter2 extends RouteBuilder {

	@Autowired
	private GetCurrentTimeBean2 getCurrentTimeBean;
	
	@Autowired
	private SimpleLoggingProcessingComponent2 loggingComponent;
	
	@Override
	public void configure() throws Exception {
		// queue / timer
		from("timer:first-timer")
		// .log("${body}")
		// .transform().constant("Time now is " + LocalDateTime.now())
		// .log("${body}")
		.bean("getCurrentTimeBean")
		// .bean(getCurrentTimeBean)
		// .bean(getCurrentTimeBean, "getCurrentTime")
		// .log("${body}")
		// .bean(loggingComponent)
		// .log("${body}")
		.process(new SimpleLoggingProcessor2())
		.to("log: first-timer");
		// transformation
		// database / log
	}
	
}

@Component
class GetCurrentTimeBean2 {
	public String getCurrentTime() {
		return "Time now is " + LocalDateTime.now();
	}
}

@Component
class SimpleLoggingProcessingComponent2 {
	
	private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessingComponent2.class);
	
	public void process(String message) {
		logger.info("SimpleLoggingProcessingComponent2 {}", message);
	}
}

class SimpleLoggingProcessor2 implements Processor {

	private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessingComponent2.class);
	
	@Override
	public void process(Exchange exchange) throws Exception {
		logger.info("SimpleLoggingProcessor2 {}", exchange.getMessage().getBody());
	}
	
}
