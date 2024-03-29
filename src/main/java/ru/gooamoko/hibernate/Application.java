package ru.gooamoko.hibernate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {
		"ru.gooamoko.hibernate.example1.repository",
		"ru.gooamoko.hibernate.example2.repository",
		"ru.gooamoko.hibernate.example3.repository",
		"ru.gooamoko.hibernate.example4.repository",
		"ru.gooamoko.hibernate.example5.repository",
		"ru.gooamoko.hibernate.example6.repository",
		"ru.gooamoko.hibernate.example7.repository",
		"ru.gooamoko.hibernate.example8.repository"
})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
