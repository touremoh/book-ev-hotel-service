package com.bookevhotel.core.controller;

import com.bookevhotel.core.dto.UserDTO;
import com.bookevhotel.core.mapper.requests.UserRequestParamsMapper;
import com.bookevhotel.core.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController extends AbstractBookEVHotelController<UserDTO> {
	public UserController(UserService service, UserRequestParamsMapper rpm) {
		super(service, rpm);
	}
}
