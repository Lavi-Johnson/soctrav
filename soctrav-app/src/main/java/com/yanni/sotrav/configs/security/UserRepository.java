package com.yanni.sotrav.configs.security;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yanni.sotrav.models.User;

//not working!!!!!
public interface UserRepository extends JpaRepository<User, Long> {

	//User findByUsername(String username);
}