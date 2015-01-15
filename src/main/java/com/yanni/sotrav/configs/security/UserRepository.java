package com.yanni.sotrav.configs.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.yanni.sotrav.models.User;

public interface UserRepository extends JpaRepository<User, Long> {


}