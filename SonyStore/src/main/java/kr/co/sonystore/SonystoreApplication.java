package kr.co.sonystore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SonystoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(SonystoreApplication.class, args);
	}

}
