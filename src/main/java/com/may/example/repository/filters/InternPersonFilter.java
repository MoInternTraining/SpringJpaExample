package com.may.example.repository.filters;

import com.may.example.model.QInternPerson;
import com.querydsl.core.types.dsl.BooleanExpression;

public class InternPersonFilter {
	
	public static BooleanExpression withEmail(String email) {
		return QInternPerson.internPerson.email.eq(email);
	}
	
	public static BooleanExpression withPhoneNo(String phoneNo) {
		return QInternPerson.internPerson.phoneNo.eq(phoneNo);
	}

}
