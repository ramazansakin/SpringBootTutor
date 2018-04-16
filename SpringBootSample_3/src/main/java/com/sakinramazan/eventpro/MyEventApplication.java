package com.sakinramazan.eventpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
// JPA in sağlamış olduğu auditleri(loglama) aktive etmeyi sağlar.
@EnableJpaAuditing
public class MyEventApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyEventApplication.class, args);
	}
}
