package com.bookevhotel.core.dto.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.Decimal128;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Price {
    private Decimal128 amount;
    private String currency;
}