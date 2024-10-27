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
public class MyFirstTimerRouter extends RouteBuilder {

	@Autowired
	private GetCurrentTimeBean getCurrentTimeBean;
	
	@Autowired
	private SimpleLoggingProcessingComponent loggingComponent;
	
	@Override
	public void configure() throws Exception {
		// queue / timer
		from("timer:first-timer")
		.log("${body}")
		//.transform().constant("Time now is " + LocalDateTime.now())
		//.bean("getCurrentTimeBean")
		// .bean(getCurrentTimeBean)
		.bean(getCurrentTimeBean, "getCurrentTime")
		.log("${body}")
		.bean(loggingComponent)
		.to("log: first-timer");
		// transformation
		// database / log
	}
	
}

@Component
class GetCurrentTimeBean {
	public String getCurrentTime() {
		return "Time now is " + LocalDateTime.now();
	}
}

@Component
class SimpleLoggingProcessingComponent {
	
	private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessingComponent.class);
	
	public void process(String message) {
		logger.info("SimpleLoggingProcessingComponent {}", message);
	}
}
