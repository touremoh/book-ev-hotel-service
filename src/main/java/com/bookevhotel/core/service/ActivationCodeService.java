package com.bookevhotel.core.service;

import com.bookevhotel.core.dao.entity.AccountActivationCode;
import com.bookevhotel.core.dao.repository.AccountActivationCodeRepositoryImpl;
import com.bookevhotel.core.dto.AccountActivationCodeDTO;
import com.bookevhotel.core.mapper.lombok.AccountActivationCodeMapper;
import com.bookevhotel.core.validation.AccountActivationCodeValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ActivationCodeService extends AbstractBookEVHotelService<AccountActivationCode, AccountActivationCodeDTO> {
	protected ActivationCodeService(AccountActivationCodeRepositoryImpl repository, AccountActivationCodeMapper mapper, AccountActivationCodeValidation validator) {
		super(repository, mapper, validator);
	}
}
