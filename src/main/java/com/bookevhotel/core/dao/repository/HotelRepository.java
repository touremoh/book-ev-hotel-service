package com.bookevhotel.core.dao.repository;

import com.bookevhotel.core.dao.BookEVHotelRepository;
import com.bookevhotel.core.dao.entity.Hotel;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends BookEVHotelRepository<Hotel> {
}
