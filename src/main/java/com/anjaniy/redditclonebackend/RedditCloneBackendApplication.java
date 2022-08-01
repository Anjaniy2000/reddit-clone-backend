package com.anjaniy.redditclonebackend;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@OpenAPIDefinition(info = @Info(title = "Reddit Clone API", version = "1.0", description = "This API Is For Reddit Clone Frontend Application."))
public class RedditCloneBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedditCloneBackendApplication.class, args);
	}

}
