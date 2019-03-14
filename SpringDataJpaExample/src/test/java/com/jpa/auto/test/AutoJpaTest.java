package com.jpa.auto.test;

import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import com.jpa.auto.config.AutoJpaConfig;
import com.jpa.auto.domain.Person;
import com.jpa.auto.repository.PersonRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AutoJpaConfig.class}, loader = AnnotationConfigContextLoader.class)
// [app/container] , [dev/test]
@ActiveProfiles({"container","dev"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AutoJpaTest {
    
    @Autowired
    PersonRepository personRepo;
    
    @Test
    public void testEmptyCount(){
        Assert.assertEquals(0, personRepo.count());
    }
    
    @Test
    public void testFindPersonWithFName(){
       personRepo.save(new Person("phani","kumar"));
       personRepo.save(new Person("kumar","phani"));
       
       Assert.assertEquals(1,personRepo.findPersonByFName("phani").size());
    }
 
    @Test
    public void testFindPersonWithF_And_LName(){
       personRepo.save(new Person("phani","kumar"));
       personRepo.save(new Person("","phani"));
       
       Assert.assertEquals(2,personRepo.findPersonByFNameOrLNameContains("phani").size());
    }
}
