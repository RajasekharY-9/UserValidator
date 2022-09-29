package com.infy.userdata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.infy.userdata.entity.User;

public interface UserDataRepository extends JpaRepository<User,String> {
	List<User> findByUserName(String UserName);
	
	List<User> deleteByUserName(String UserName);
	
}






