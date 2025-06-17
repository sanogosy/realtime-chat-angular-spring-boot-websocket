package com.chatapp.chat;

import com.chatapp.chat.entity.Roles;
import com.chatapp.chat.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(RoleRepository roleRepository) {
		return args -> {
			if(roleRepository.findByName("USER").isEmpty()) {
				roleRepository.save(Roles.builder().name("USER").build());
			}
		};
	}

}
