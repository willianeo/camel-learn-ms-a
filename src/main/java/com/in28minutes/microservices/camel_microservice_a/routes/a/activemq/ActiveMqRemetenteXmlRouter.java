package com.in28minutes.microservices.camel_microservice_a.routes.a.activemq;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMqRemetenteXmlRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from ("file:files/xml")
		.log("${body}")
		.to("activemq:my-activemq-xml-queue");
	}

}
