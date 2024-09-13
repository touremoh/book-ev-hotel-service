package com.bookevhotel.core.service;

import com.bookevhotel.core.dao.entity.User;
import com.bookevhotel.core.dao.repository.UserRepositoryImpl;
import com.bookevhotel.core.dto.UserDTO;
import com.bookevhotel.core.mapper.lombok.UserMapper;
import com.bookevhotel.core.validation.UserServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractBookEVHotelService<User, UserDTO> {
	@Autowired
	public UserService(UserRepositoryImpl repository, UserMapper mapper, UserServiceValidator validator) {
		super(repository, mapper, validator);
	}
}
