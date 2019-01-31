package com.jpa.repo;

import com.jpa.domain.User;

public interface UserJPARepository {
	long countUsers();
	User save(User user);
	User findUser(long id);
}
