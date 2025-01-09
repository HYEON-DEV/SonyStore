package kr.co.sonystore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
// public class SonystoreApplication extends SpringBootServletInitializer {
public class SonystoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(SonystoreApplication.class, args);
	}

    // @Override
    // protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    //     return application.sources(SonystoreApplication.class);
    // }
}



