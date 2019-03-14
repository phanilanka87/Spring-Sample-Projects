package com.jpa.auto.main;

import com.jpa.auto.config.AutoJpaConfig;
import com.jpa.auto.domain.Person;
import com.jpa.auto.repository.PersonRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RunWithActiveProfiles {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.getEnvironment().setActiveProfiles("container", "dev");
        context.register(AutoJpaConfig.class);
        context.refresh();
        PersonRepository personRepo = context.getBean(PersonRepository.class);

        personRepo.save(new Person("Phani", "Kumar"));
        personRepo.save(new Person("Pha", "Kum"));
        personRepo.saveAndFlush(new Person("hani", "umar"));
        
        System.out.println("Total Records: " + personRepo.count());
        
        System.out.println("Records starting with pha: " + personRepo.findPersonByFNameOrLNameContains("ha").size());
    }
}
