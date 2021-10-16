package com.kodluyoruz.weekfourapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WeekfourapiApplication {

	//docker run -d --name weekfour -v my_dbdata:/var/lib/postgresql/weekfour -p 54320:5432 -e POSTGRES_PASSWORD=my_password postgres

	public static void main(String[] args) {
		SpringApplication.run(WeekfourapiApplication.class, args);
	}

}
