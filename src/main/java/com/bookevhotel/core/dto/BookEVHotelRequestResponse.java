package com.bookevhotel.core.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BookEVHotelRequestResponse implements Serializable {
	private Object data;
	private int code;
	private LocalDateTime timestamp;
}
