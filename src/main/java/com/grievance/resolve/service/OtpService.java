package com.grievance.resolve.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class OtpService {
	
	private static class OtpData{
		String otp;
		LocalDateTime expiryTime;
		int attempts;
		
		OtpData(String otp, LocalDateTime expiryTime){
			this.otp=otp;
			this.expiryTime=expiryTime;
			this.attempts=0;
		}
	}
	
	private final Map<String, OtpData> otpStorage=new ConcurrentHashMap<>();
	
	public String generateOtp(String email) {
		String otp=String.valueOf(new Random().nextInt(900000)+100000);
		LocalDateTime expiry=LocalDateTime.now().plusMinutes(5);
		otpStorage.put(email, new OtpData(otp, expiry));
		return otp;
	}
	
	public boolean validateOtp(String email, String otp) {
		
		OtpData data=otpStorage.get(email);
		
//		return otp.equals(otpStorage.get(email));
		
		if(data==null) {
			return false;
		}
		
		if(LocalDateTime.now()
				.isAfter(data.expiryTime)) {
			otpStorage.remove(email);
			throw new RuntimeException("OTP Expired");
		}
		
		if(data.attempts >= 3){
			otpStorage.remove(email);
			throw new RuntimeException("TOO many Attempts");
		}
		data.attempts++;
		
		if(data.otp.equals(otp)){
			otpStorage.remove(email);
			return true;
		}
		return false;
	}

}
