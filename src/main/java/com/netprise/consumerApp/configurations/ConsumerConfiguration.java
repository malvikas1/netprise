package com.netprise.consumerApp.configurations;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;

@Configuration
@EnableKafka
public class ConsumerConfiguration {
	
	@Value("${kafka.bootstrap-servers}")
	private String bootstrapServer;
	
	@Value("${kafka.group-id}")
	private String group_id;
	
	@Value("${kafka.auto-offset-reset}")
	private String auto_offset_reset;
	
	  @Bean
	  public Map<String, Object> consumerConfigs() {
	    Map<String, Object> props = new HashMap<>();
	    // list of host:port pairs used for establishing the initial connections to the Kafka cluster
	    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
	    		bootstrapServer);
	    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
	        StringDeserializer.class);
	    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
	        JsonSerializer.class);
	    // allows a pool of processes to divide the work of consuming and processing records
	    props.put(ConsumerConfig.GROUP_ID_CONFIG, group_id);
	    // automatically reset the offset to the earliest offset
	    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, auto_offset_reset);

	    return props;
	  }

	  @Bean
	  public ConsumerFactory<String, String> consumerFactory() {
	    return new DefaultKafkaConsumerFactory<>(consumerConfigs());
	  }

	  @Bean
	  public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
	    ConcurrentKafkaListenerContainerFactory<String, String> factory =
	        new ConcurrentKafkaListenerContainerFactory<>();
	    factory.setConsumerFactory(consumerFactory());

	    return factory;
	  }
	
	

}
