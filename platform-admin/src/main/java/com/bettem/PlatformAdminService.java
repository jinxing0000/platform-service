package com.bettem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@MapperScan(basePackages = {"com.bettem.modules.*.dao"})
@EnableAsync
public class PlatformAdminService extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(PlatformAdminService.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PlatformAdminService.class);


	}
}
