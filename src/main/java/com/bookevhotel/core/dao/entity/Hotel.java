package com.bookevhotel.core.dao.entity;

import com.bookevhotel.core.dao.BookEVHotelEntity;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Document(collection = "hotels")
public class Hotel implements BookEVHotelEntity {
	@Id
	private ObjectId id;
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
	public ObjectId getId() {
		return id;
	}
}

