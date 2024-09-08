package com.bookevhotel.core.service;

import com.bookevhotel.core.dao.entity.User;
import com.bookevhotel.core.dao.repository.UserRepository;
import com.bookevhotel.core.dto.UserDTO;
import com.bookevhotel.core.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractBookEVHotelService<User, UserDTO> {
	public UserService(UserRepository repository, UserMapper mapper) {
		super(repository, mapper);
	}
}
