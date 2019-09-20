package com.netprise.consumerApp;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.netprise.consumerApp.kafka.Receiver;

@SpringBootApplication
public class ConsumerAppApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(ConsumerAppApplication.class, args);
		
		Receiver receiver = ctx.getBean(Receiver.class);
		
		try {
			receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
