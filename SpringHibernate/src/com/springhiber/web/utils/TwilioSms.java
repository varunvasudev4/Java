package com.springhiber.web.utils;

import com.twilio.Twilio; 
import com.twilio.converter.Promoter; 
import com.twilio.rest.api.v2010.account.Message; 
import com.twilio.type.PhoneNumber; 
 
import java.net.URI; 
import java.math.BigDecimal; 
 
public class TwilioSms { 
    // Find your Account Sid and Token at twilio.com/console 
    public static final String ACCOUNT_SID = "ACa7025091db8efadc8808135b957e5ded"; 
    public static final String AUTH_TOKEN = "06fb1d4938e9857b54848da872d91f79"; 
 
    public static void sendSms(String contactnumer , String temppass) { 
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN); 
        Message message = Message.creator( 
                new com.twilio.type.PhoneNumber("+91"+contactnumer), 
                new com.twilio.type.PhoneNumber("+12029465354"),  
                "Your Temporory Password is "+temppass)      
            .create(); 
 
        System.out.println(message.getSid()); 
    } 
}