package com.studentmng.stumngdiscoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class StumngdiscoveryserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(StumngdiscoveryserverApplication.class, args);
	}

}
