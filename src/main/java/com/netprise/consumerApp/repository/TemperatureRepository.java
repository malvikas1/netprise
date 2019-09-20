package com.netprise.consumerApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.netprise.consumerApp.model.TemperatureInfo;

public interface TemperatureRepository extends JpaRepository<TemperatureInfo, Integer> {

}
