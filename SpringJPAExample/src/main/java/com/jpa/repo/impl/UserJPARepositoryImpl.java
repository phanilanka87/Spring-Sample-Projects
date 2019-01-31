package com.jpa.repo.impl;

import com.jpa.domain.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import com.jpa.repo.UserJPARepository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserJPARepositoryImpl implements UserJPARepository{

	@PersistenceContext
	private EntityManager emf;
	//private EntityManagerFactory emf
	
	
	@Override
	public long countUsers() {
		Query query = emf.createQuery("select u from User u");
		List<User> users = query.getResultList();
		return users.size();
	}

	@Override
	public User save(User user) {
		emf.persist(user);
		return user;
	}

	@Override
	public User findUser(long id) {
		return emf.find(User.class, id);
	}
	
}
