package com.hukahuka.mail.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserMail {
	
	 private String address;
	 
    private String title;
    
    private String message;
}
