package com.event.business.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.event.business.model.OtpDetails;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@RestController
public class SmsController {

	Logger logger = LoggerFactory.getLogger(SmsController.class);

	Map<String, OtpDetails> otpMap = new HashMap<>();

	private static final long EXPIRY_TIME = 60000;

	private static final String FROM_PHONE_NO = "";

	private static final String OTP_MESSAGE = "This is your otp: ";

	private static final String OTP_MESSAGE_1 = " generated by tabOnEvent ";

	private static final String OTP_SUCCESS_MSG = "OTP sent successfully";

	private static final String OTP_ADMIN_MSG = "Has some issues please contact Administrator";

	private static final String OTP_EXPIRY_MSG = "OTP has expired";

	private static final String OTP_INVALID_MSG = "Invalid OTP";

	private static final String OTP_VERIFY_SUCCESS_MSG = "OTP verified successfully";

	private static final String OTP_MISSING_MSG = "please provide OTP";

	private static final String TWILIO_ACCOUNT_ID = "";

	private static final String TWILIO_AUTH_ID = "";

	static {
		Twilio.init(TWILIO_ACCOUNT_ID, TWILIO_AUTH_ID);
	}

	@PostMapping(path = "/phonenumber/{phone_number}/otp")
	public ResponseEntity<Object> sendOtp(@PathVariable("phone_number") String phoneNumber) throws Exception {
		OtpDetails otp = new OtpDetails();
		otp.setPhoneNumber(phoneNumber);
		otp.setExpiryTime(System.currentTimeMillis() + EXPIRY_TIME);
		otp.setOtp(String.valueOf(getRandomNumber()));
		otpMap.put(phoneNumber, otp);
		Message.creator(new PhoneNumber(phoneNumber), new PhoneNumber(FROM_PHONE_NO),
				OTP_MESSAGE + otp.getOtp() + OTP_MESSAGE_1).create();
		return new ResponseEntity<>(OTP_SUCCESS_MSG, HttpStatus.OK);
	}

	private int getRandomNumber() {
		// return ((int) (Math.random() * (10000 - 1000))) * 1000;
		return ThreadLocalRandom.current().nextInt(100000, 1000000);
	}

	@PutMapping(path = "/phonenumber/{phone_number}/otp")
	public ResponseEntity<Object> verifyOtp(@PathVariable("phone_number") String phoneNumber,
			@RequestBody OtpDetails otpDetails) throws Exception {
		if (StringUtils.isEmpty(otpDetails.getOtp())) {
			return new ResponseEntity<>(OTP_MISSING_MSG, HttpStatus.BAD_REQUEST);
		}
		if (otpMap.containsKey(phoneNumber)) {
			OtpDetails otpDets = otpMap.get(phoneNumber);
			if (otpDets.getExpiryTime() >= System.currentTimeMillis()) {
				if (otpDetails.getOtp().equals(otpDets.getOtp())) {
					otpMap.remove(phoneNumber);
					return new ResponseEntity<>(OTP_VERIFY_SUCCESS_MSG, HttpStatus.OK);
				}
				return new ResponseEntity<>(OTP_INVALID_MSG, HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(OTP_EXPIRY_MSG, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(OTP_ADMIN_MSG, HttpStatus.BAD_REQUEST);
	}

}
