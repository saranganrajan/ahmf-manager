package com.saranganrajan.apps.ahmfmanager;

import com.saranganrajan.apps.ahmfmanager.external.audit.feign.AuditServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients("com.saranganrajan.apps.ahmfmanager")
@SpringBootApplication
public class AhmfManagerApplication {

	@Autowired
	AuditServiceFeignClient feignClient;

	public static void main(String[] args) {
		SpringApplication.run(AhmfManagerApplication.class, args);
	}

}
