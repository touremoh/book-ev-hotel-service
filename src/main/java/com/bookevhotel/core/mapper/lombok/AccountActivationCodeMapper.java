package com.bookevhotel.core.mapper.lombok;

import com.bookevhotel.core.dao.entity.AccountActivationCode;
import com.bookevhotel.core.dto.AccountActivationCodeDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountActivationCodeMapper extends BookEVHotelMapper<AccountActivationCode, AccountActivationCodeDTO> {
}
