package com.picotrake.API;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		String port = System.getenv("PORT");
		System.out.println("🧪 PORT desde entorno: " + port);
		SpringApplication.run(Application.class, args);
	}
}
