package com.jpa.main;

import com.jpa.config.AppJPAConfig;
import com.jpa.repo.UserJPARepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RunWithActiveProfile {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context
				= new AnnotationConfigApplicationContext();

		context.getEnvironment().setActiveProfiles("app", "dev");
		context.register(AppJPAConfig.class);
		context.refresh();

		UserJPARepository userRepo = context.getBean(UserJPARepository.class);
		System.out.println(userRepo.countUsers());
	}

}
