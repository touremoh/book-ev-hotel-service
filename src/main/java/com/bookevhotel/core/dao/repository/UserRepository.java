package com.bookevhotel.core.dao.repository;

import com.bookevhotel.core.dao.BookEVHotelRepository;
import com.bookevhotel.core.dao.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BookEVHotelRepository<User> {
}
