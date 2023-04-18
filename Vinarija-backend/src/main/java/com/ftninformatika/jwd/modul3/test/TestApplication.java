package com.ftninformatika.jwd.modul3.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
public class TestApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		 SpringApplication.run(TestApplication.class, args);
	}

}
