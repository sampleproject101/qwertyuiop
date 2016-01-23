package com.chua.evergrocery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:META-INF/spring/hibernate.xml")
public class Application {

	public static final int ITEMS_PER_PAGE = 10;
	
	public static void main(String ... args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
}
