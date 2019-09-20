package com.netprise.consumerApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netprise.consumerApp.model.TemperatureInfo;
import com.netprise.consumerApp.repository.TemperatureRepository;

@Controller
public class ConsumerController {
	
	@Autowired
	private TemperatureRepository temperatureRepository;
	
	@RequestMapping("/")
	@ResponseBody
	public List<TemperatureInfo> getAllTemperatureInfo(){
		List<TemperatureInfo> list = temperatureRepository.findAll();
		System.out.println(list.size());
		return list;
	}
	
}
