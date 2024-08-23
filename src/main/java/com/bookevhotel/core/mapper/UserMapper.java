package com.bookevhotel.core.mapper;

import com.bookevhotel.core.dao.entity.User;
import com.bookevhotel.core.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends BookEVHotelMapper<User, UserDTO> {
}
