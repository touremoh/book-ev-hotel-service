package com.bookevhotel.core.mapper.lombok;

import com.bookevhotel.core.dao.entity.MailingList;
import com.bookevhotel.core.dto.MailingListDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MailingListMapper extends BookEVHotelMapper<MailingList, MailingListDTO> {
}
