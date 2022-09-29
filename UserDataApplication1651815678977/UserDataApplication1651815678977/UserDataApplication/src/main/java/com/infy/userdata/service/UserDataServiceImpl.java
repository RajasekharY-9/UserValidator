package com.infy.userdata.service;

import java.util.ArrayList;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.userdata.dto.UserDTO;
import com.infy.userdata.entity.User;
import com.infy.userdata.exception.UserDataException;
import com.infy.userdata.repository.UserDataRepository;
import com.infy.userdata.validator.UserValidator;


@Service(value="userDataService")
@Transactional
public class UserDataServiceImpl implements UserDataService {

	@Autowired
	private UserDataRepository userDataRepository;

	@Override
	public Integer addUser(UserDTO userDTO) throws UserDataException  {
		UserValidator userValidator=new UserValidator();
		userValidator.validate(userDTO);
		User user=new User();
		user.setUserId(userDTO.getUserId());
		user.setCity(userDTO.getCity());
		user.setPhoneNo(userDTO.getPhoneNo());
		user.setPassword(userDTO.getPassword());
		user.setUserName(userDTO.getUserName());
		userDataRepository.save(user);
		return user.getUserId();
		
		
	}

	@Override
	public List<UserDTO> getDetailsByUserName(String userName) throws UserDataException {
		List<User> userList=userDataRepository.findByUserName(userName);
		if(userList.isEmpty()) {
			throw new UserDataException("Service.NO_DETAILS_FOUND");
			
		}
		List<UserDTO> userDTO=new ArrayList<UserDTO>();
		for(User users:userList) {
			UserDTO usersDetails=new UserDTO();
			usersDetails.setCity(users.getCity());
			usersDetails.setPassword(users.getPassword());
			usersDetails.setPhoneNo(users.getPhoneNo());
			usersDetails.setUserName(users.getUserName());
			usersDetails.setUserId(users.getUserId());
			userDTO.add(usersDetails);
			
			
		}
		return userDTO;
		
	}
	
}



