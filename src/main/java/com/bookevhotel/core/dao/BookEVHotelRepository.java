package com.bookevhotel.core.dao;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BookEVHotelRepository<E extends BookEVHotelEntity> extends MongoRepository<E, ObjectId> {
}
