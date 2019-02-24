package com.jpa.main;

import com.jpa.config.AppJPAConfig;
import com.jpa.repo.UserJPARepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.AbstractEnvironment;

public class RunWithSystemProfile {

	public static void main(String[] args) {

		System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "app,dev");
		
		AnnotationConfigApplicationContext context
				= new AnnotationConfigApplicationContext(AppJPAConfig.class);
		
		UserJPARepository userRepo = context.getBean(UserJPARepository.class);
		System.out.println(userRepo.countUsers());

	}

}
