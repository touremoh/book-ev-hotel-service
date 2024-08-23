package com.bookevhotel.core.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class HotelDTO implements BookEVHotelDTO {
	@Id
	private String id;
	private String hotelName;
	private Location location;
	private String websiteLink;
	private BillingInformation billingInformation;
	private Subscription subscription;
	private List<PaymentHistory> paymentHistory;
	private List<String> amenities;

	public record Location(String fullAddress, String city, String postalCode, String country) {}
	public record BillingInformation(String nameOnCard, String cardNumber, LocalDate expirationDate, String cvcCode, Address billingAddress) {}
	public record Subscription(String currentPlan, BigDecimal price, String billingCycle, String accountStatus) {}
	public record PaymentHistory(LocalDate paymentDate, String cardLastFour, BigDecimal amount) {}
	public record Address(String streetAddress, String number, String postalCode, String city, String country) {}

	@Override
	public String getId() {
		return id;
	}
}

