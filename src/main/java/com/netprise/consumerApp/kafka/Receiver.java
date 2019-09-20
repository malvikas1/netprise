package com.netprise.consumerApp.kafka;

import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.netprise.consumerApp.model.TemperatureInfo;
import com.netprise.consumerApp.repository.TemperatureRepository;

@Component
public class Receiver {
	
	@Autowired
	private TemperatureRepository  temperatureRepository;
	
	private CountDownLatch latch = new CountDownLatch(1);

	  public CountDownLatch getLatch() {
	    return latch;
	  }
	  
	  @KafkaListener(topics = "tempTopic")
	  public void receive(TemperatureInfo temp) {
		temperatureRepository.save(temp);
	    latch.countDown();
	  }

}
