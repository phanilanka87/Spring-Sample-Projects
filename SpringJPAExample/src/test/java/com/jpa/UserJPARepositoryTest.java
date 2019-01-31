package com.jpa;

import com.jpa.config.AppJPAConfig;
import com.jpa.config.ContainerJPAConfig;
import com.jpa.domain.User;
import com.jpa.repo.UserJPARepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/*
ContainerJPAConfig.class : LocalContainnerEntityManagerFactory
AppJPAConfig.class : LocalEntityManagerFactory. This will get the datasource from persistence.xml.
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ContainerJPAConfig.class,AppJPAConfig.class}, loader = AnnotationConfigContextLoader.class)
// [app/container] , [dev/test]
@ActiveProfiles({"container","dev"})
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class UserJPARepositoryTest {
	
	@Autowired
	UserJPARepository userRepo;
	
	@Test
	public void testInitialCount(){
		long count = userRepo.countUsers();
		Assert.assertEquals(0, count);
	}
	
	@Test
	public void testInserUser(){
		User usr = new User();
		usr.setName("Phani");
		userRepo.save(usr);
		Assert.assertEquals(1, userRepo.countUsers());
	}
	
	@Test
	public void testFindUser(){
		User usr = new User();
		usr.setName("SRI");
		User foundUser = userRepo.save(usr);
//		Assert.assertNotNull(foundUser);
		Assert.assertEquals("SRI", foundUser.getName());
	}
}
