package com.jpa.auto.main;

import com.jpa.auto.config.AutoJpaConfig;
import com.jpa.auto.domain.Person;
import com.jpa.auto.repository.PersonRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.AbstractEnvironment;

public class RunWithSystemProfile {

    public static void main(String[] args) {

        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "container,dev");

        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(AutoJpaConfig.class);
        PersonRepository personRepo = context.getBean(PersonRepository.class);

        personRepo.save(new Person("Phani", "Kumar"));
        personRepo.save(new Person("Pha", "Kum"));
        personRepo.saveAndFlush(new Person("hani", "umar"));

        System.out.println("Total Records: " + personRepo.count());

        System.out.println("Records with fName eq Phani: " + personRepo.findPersonByFName("Phani").size());
        
        System.out.println("Records containing with pha: " + personRepo.findPersonByFNameOrLNameContains("ha").size());
    }

}
